import requests
import pymysql
import re

API_KEY = "4450565522634f6da4fe"
BASE_URL = "http://openapi.foodsafetykorea.go.kr/api"

DB_CONFIG = {
    "host": "yummy.crgiqay22xf2.ap-northeast-2.rds.amazonaws.com",
    "user": "yummy",
    "password": "Dkan2032$%",
    "database": "yummy",
    "charset": "utf8mb4"
}
def clean_text(value):
    if value is None:
        return None

    value = str(value).strip()

    return value if value else None


def to_decimal(value):
    try:
        value = clean_text(value)

        if value is None:
            return None

        # 숫자 + 소수점만 남김
        value = re.sub(r"[^0-9.]", "", value)

        if value == "":
            return None

        return float(value)

    except:
        return None


def insert_recipes(start=1, end=10):

    url = f"{BASE_URL}/{API_KEY}/COOKRCP01/json/{start}/{end}"

    print(f"{start}~{end} 요청 중...")

    response = requests.get(url)

    data = response.json()

    rows = data.get("COOKRCP01", {}).get("row", [])

    if not rows:
        print(f"{start}~{end} 가져온 데이터 없음")
        return 0

    conn = pymysql.connect(**DB_CONFIG)

    cursor = conn.cursor()

    saved_count = 0

    try:

        for item in rows:

            rcp_api_seq = clean_text(item.get("RCP_SEQ"))

            rcp_name = clean_text(item.get("RCP_NM"))

            if not rcp_api_seq or not rcp_name:
                continue

            sql = """
                INSERT INTO recipe (
                    rcp_api_seq,
                    rcp_name,
                    rcp_way,
                    rcp_type,
                    rcp_weight,
                    rcp_kcal,
                    rcp_carbs,
                    rcp_protein,
                    rcp_fat,
                    rcp_natrium,
                    rcp_image,
                    rcp_parts
                )
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)

                ON DUPLICATE KEY UPDATE
                    rcp_name = VALUES(rcp_name),
                    rcp_way = VALUES(rcp_way),
                    rcp_type = VALUES(rcp_type),
                    rcp_weight = VALUES(rcp_weight),
                    rcp_kcal = VALUES(rcp_kcal),
                    rcp_carbs = VALUES(rcp_carbs),
                    rcp_protein = VALUES(rcp_protein),
                    rcp_fat = VALUES(rcp_fat),
                    rcp_natrium = VALUES(rcp_natrium),
                    rcp_image = VALUES(rcp_image),
                    rcp_parts = VALUES(rcp_parts)
            """

            cursor.execute(sql, (
                rcp_api_seq,
                rcp_name,
                clean_text(item.get("RCP_WAY2")),
                clean_text(item.get("RCP_PAT2")),
                clean_text(item.get("INFO_WGT")),
                to_decimal(item.get("INFO_ENG")),
                to_decimal(item.get("INFO_CAR")),
                to_decimal(item.get("INFO_PRO")),
                to_decimal(item.get("INFO_FAT")),
                to_decimal(item.get("INFO_NA")),
                clean_text(item.get("ATT_FILE_NO_MAIN")),
                clean_text(item.get("RCP_PARTS_DTLS"))
            ))

            # recipe 번호 가져오기
            cursor.execute(
                "SELECT rcp_num FROM recipe WHERE rcp_api_seq = %s",
                (rcp_api_seq,)
            )

            rcp_num = cursor.fetchone()[0]

            # 기존 step 삭제
            cursor.execute(
                "DELETE FROM recipe_step WHERE rcp_num = %s",
                (rcp_num,)
            )

            # step 저장
            for i in range(1, 21):

                manual_key = f"MANUAL{i:02d}"

                step_text = clean_text(item.get(manual_key))

                if step_text:

                    cursor.execute("""
                        INSERT INTO recipe_step (
                            rcp_num,
                            step_order,
                            step_text
                        )
                        VALUES (%s, %s, %s)
                    """, (
                        rcp_num,
                        i,
                        step_text
                    ))

            saved_count += 1

        conn.commit()

        print(f"{start}~{end} 저장 완료! ({saved_count}개)")

        return saved_count

    except Exception as e:

        conn.rollback()

        print(f"{start}~{end} 오류 발생:", e)

        return 0

    finally:

        cursor.close()

        conn.close()


# 마지막 저장된 API 번호 가져오기
def get_last_api_seq():

    conn = pymysql.connect(**DB_CONFIG)

    cursor = conn.cursor()

    try:

        cursor.execute("""
            SELECT MAX(CAST(rcp_api_seq AS UNSIGNED))
            FROM recipe
        """)

        last_seq = cursor.fetchone()[0]

        return last_seq if last_seq else 0

    finally:

        cursor.close()

        conn.close()


# 자동 이어받기
last_seq = get_last_api_seq()

start_num = last_seq + 1

print(f"마지막 저장 번호: {last_seq}")

total_count = 0

for start in range(start_num, 1001, 100):

    end = start + 99

    if end > 1000:
        end = 1000

    total_count += insert_recipes(start, end)

print(f"전체 완료! 총 {total_count}개 저장/업데이트")
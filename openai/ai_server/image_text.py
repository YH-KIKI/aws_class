# 이미지 + 텍스트
# 예 : 이미지에서 사과가 몇개 있어?
# 결과 : 사과 7개 있어요 => 사과가 어디에 있는지 네모칸 치기

import os

GOOGLE_API_KEY = os.getenv("GOOGLE_API_KEY")
GOOGLE_MODEL_NAME = "gemini-2.5-flash-lite"

# 이미지에서 사용자 질문에 맞는 결과를 반환
# 사과가 어디에 있는지(위치)
def get_ai_vision_result(image_path, user_prompt):  # 파일위치 / 질문
  with open(image_path, "rb") as f:
    image_bytes = f.read()

  prompt = """
  지룸ㄴ : {user_prompt}
  이미지에서 해당 객체를모두 찾아주세요.
  출력형식은 반드시 [객체명, ymin, xmin, ymax, xmax] 리스트 형태로만 나열하세요.
  객체명은 영문으로 해주세요.
  예 : [apple. 200, 150, 400, 300]
  """
  pass

result = get_ai_vision_result('apple.jpg', '사과가 몇개 있어?')
print(result)
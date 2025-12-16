# 윈도우 함수
# - 테이블의 행과 행사이의 관계를 정리하기 위해 제공하는 함수
# - OVER절이 들어간 함수
# - 순위를 표현할 때 사용

# ROW_NUMBER : 값이 같더라도 다른 번호를 부여 
# - 1, 2, 3, 4, 5
# RANK : 값이 같으면 같은 등수로 표시, 다음 등수는 같은 등수만큼 건너 뛰어서 진행 
# - 1, 2, 2, 4, 4
# DENSE_RANK : 값이 같으면 같은 등수로 표시, 다음 등수는 이어서 진행
# - 1, 2, 2, 3, 4

# SELECT 컬럼, 윈도우함수() OVER(ORDER BY 정렬) '별명' FROM 테이블;
USE SHOPPINGMALL2;
SELECT ROW_NUMBER() OVER(ORDER BY PRICE DESC) 순위,
	PRODUCT.*
	FROM PRODUCT;
SELECT RANK() OVER(ORDER BY PRICE DESC) 순위,
	PRODUCT.*
	FROM PRODUCT;
SELECT DENSE_RANK() OVER(ORDER BY PRICE DESC) 순위,
	PRODUCT.*
	FROM PRODUCT;
    
# 가격이 제일 높은 제품 3개를 조회(ROW_NUMBER)
SELECT * 
FROM(SELECT ROW_NUMBER() OVER(ORDER BY PRICE DESC) 순위,
	PRODUCT.*
	FROM PRODUCT) T
WHERE 순위 <= 3;

# NTILE
# - 검색 결과 전체를 지정된 그룹으로 분할하여 번호를 할당
# - 등급 간격이 동일할 때 활용 (ex. 학생 성적 등급 등)
# SELECT NTILE(그룹수) OVER(ORDER BY 정렬), 컬럼 FROM 테이블;
# 제품을 가격순으로 내림차순으로 정렬하여 2개의 그룹(비싼거, 싼거)로 나눔
SELECT NTILE(2) OVER(ORDER BY PRICE DESC), PRODUCT.* FROM PRODUCT;


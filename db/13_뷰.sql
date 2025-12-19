# 뷰
# - 사용자에게는 일반 테이블과 똑같이 보이는 가상 테이블 
# - SELECT문이 저장된 객체
# - 왜?
# 	- 보안 : 민감한 컬럼을 숨겨 필요한 정보만 보여줌
#	- 편의성 : 여러개의 테이블을 JOIN한 뷰를 만들면 JOIN없이 결과를 뷰를 통해 확인
#	- 독립성 : 원본 테이블 구조가 바뀌어도 뷰의 쿼리만 수정하면 기존 방식 그대로 조회가 가능

# 뷰 생성
/*
CREATE VIEW 뷰이름 AS
SELECT문;
*/
# 강의 조회시 강의명(과목명)을 조회하기 위한 뷰를 생성
CREATE VIEW LECTURE_SUBJECT AS
SELECT * FROM LECTURE JOIN SUBJECT ON LT_SJ_CODE = SJ_CODE;

# 뷰 조회
# SELECT * FROM 뷰이름
SELECT * FROM LECTURE_SUBJECT;

# 뷰 삭제
# DROP VIEW 뷰이름;
DROP VIEW LECTURE_SUBJECT;

# 생성된 뷰 조회
SHOW FULL TABLES;

# 뷰만 조회
SHOW FULL TABLES WHERE TABLE_TYPE = 'VIEW';

# 수강에 과목 정보도 포함되는 VIEW를 생성
CREATE VIEW COURSE_SUBJECT AS
SELECT * FROM COURSE
	JOIN LECTURE ON CO_LT_NUM = LT_NUM
    JOIN SUBJECT ON LT_SJ_CODE = SJ_CODE;
    
# 학생별 총 이수학점을 조회
SELECT ST_NUM, ST_NAME, IFNULL(SUM(SJ_POINT), 0) 총이수학점 FROM
	( SELECT * FROM COURSE_SUBJECT
		WHERE
			CO_SCORE IS NOT NULL AND CO_SCORE NOT IN('F', 'FAIL')) C
		RIGHT JOIN STUDENT ON ST_NUM = CO_ST_NUM
        GROUP BY ST_NUM;
        
# 교수가 학생을 조회할 때 주민번호가 안보이게 하기 위해 뷰를 생성
CREATE VIEW VIEW_STUDENT AS
SELECT ST_NUM, ST_NAME, ST_CONTACT, ST_YEAR FROM STUDENT;

SELECT * FROM VIEW_STUDENT;


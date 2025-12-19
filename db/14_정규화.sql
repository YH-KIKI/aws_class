# 정규화
# - DB 설계에서 데이터 중복을 최소화하고, 무결성을 유지하기 위해 데이터를 구조화 하는 과정
USE NF;
# 제1정규화
# - 컬럼의 값이 원자값이 되도록 정규화
# 	-> 값이 복수값이 없도록 정규화
#	-> 샘플 데이터에서 PHONE_NUMBER의 값이 ,를 통해 여러개 되어 있음
# - 값이 원자값이 되도록 행을 추가하거나 테이블을 분리
UPDATE ENROLLMENT_UNF
	SET PHONE_NUMBER = '010-1111'
    WHERE STUDENT_ID = 101;
INSERT INTO enrollment_unf 
VALUES (101, '홍길동', '010-2222', 'CS01', '데이터베이스', '홍길동', '301호', 'A');

INSERT INTO enrollment_unf 
VALUES (101, '홍길동', '010-2222', 'CS02', '알고리즘', '김철수', '302호', 'B');

# 원자값이 되도록 행을 추가했지만 2정규화에서 학생 테이블을 만들 때 불편하기 때문에 다음
# CONTACT 테이블을 생성
DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT(
	STUDENT_ID INT,
    PHONE_NUMBER VARCHAR(100)
);
INSERT INTO CONTACT(STUDENT_ID, PHONE_NUMBER)
VALUES('101', '010-1111'), ('101', '010-2222'), ('102', '010-3333');

ALTER TABLE ENROLLMENT_UNF DROP PHONE_NUMBER;

DELETE FROM ENROLLMENT_UNF WHERE STUDENT_ID = 101;
INSERT INTO ENROLLMENT_UNF
VALUES(101, '홍길동', 'CS01', '데이터베이스', '홍길동', '301호', 'A');
INSERT INTO ENROLLMENT_UNF
VALUES(101, '홍길동', 'CS02', '알고리즘', '김철수', '302호', 'B');
   
# 제 2 정규화  
# - 기본키가 복합키일 때, 컬럼이 기본키 일부에 종속이 되면 불리한다.
# - 부분 함수 종속을 제거 -> 테이블을 나눔
# - 샘플에서 기본키는 STUDENT_ID, COURSE_ID가 기본키라고 할 때,
# 	학생이름과 연락처는 SUTDENT_ID에 종속 -> 부분 함수 종속
# 	강의명은 COURSE_ID에 종속이고 -> 부분 함수 종속
#	학생 성적은 STUDENT_ID와 COURSE_ID에 종속 -> 완전 함수 종속

# ENROLLMENT_UNF 테이블에서 학생관련 정보는 STUDENT 테이블에,
# 수강 관련 정보는 수강 테이블에 나누어서 관리

CREATE TABLE STUDENT(
	STUDENT_ID INT,
    STUDENT_NAME VARCHAR(20)
);

INSERT INTO STUDENT VALUE(101, '홍길동'), (102, '김철수');
ALTER TABLE ENROLLMENT_UNF DROP STUDENT_NAME;
# 과목 정보는 과목 테이블에 나누어서 관리
CREATE TABLE SUBJECT(
	COURSE_ID CHAR(4),
    COURSE_NAME VARCHAR(50),
    PROFESSOR_NAME VARCHAR(20),
    PROFESSOR_ROOM VARCHAR(20)
);
INSERT INTO SUBJECT VALUES('CS01', '데이터베이스', '홍길동', '301호'),
('CS02', '알고리즘', '김철수', '302호');
ALTER TABLE ENROLLMENT_UNF 
	DROP COURSE_NAME,
    DROP PROFESSOR_NAME,
    DROP PROFESSOR_ROOM;

# 제3정규화
# - 기본키가 아닌 속성들끼리 종속 관계가 있으면 분리
# - 이행 함수 종속 제거
# - COURSE 테이블에서 교수실이 교수에 의해 결정
# 	 -> 교수 테이블을 만들어서 분리
CREATE TABLE PROFESSOR(
	PROFESSOR_NAME VARCHAR(20),
    PROFESSOR_ROOM VARCHAR(20)
);
INSERT INTO PROFESSOR VALUES('홍길동', '301호'), ('김철수', '302호');
ALTER TABLE SUBJECT DROP PROFESSOR_ROOM;
SELECT * FROM SUBJECT;


# 반 정규화
# - 정규화를 통해 쪼개진 테이블이나 컬럼을 합치거나 추가하는 것
# - 왜? 성능 향상을 위해
# 	- 너무 나누었더니 JOIN이 너무 많이 발생
#	-> 중복되는걸 알지만 컬럼을 추가해서 JOIN 횟수를 줄임 





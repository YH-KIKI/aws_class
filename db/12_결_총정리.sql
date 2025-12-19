/*
SQL
 - DDL : 데이터 정의어 
 - DML : 데이터 조작어 
 - DCL : 데이터 제어어 
 - TCL : 트랜잭션 제어어 
 
DDL (2번 파일)
 - DB, 테이블을 추가,수정,삭제 
 - CREATE, DROP 
 - CREATE DATABASE [IF NOT EXISTS] DB명;
 - CREATE TABLE IF NOT EXISTS 테이블명(
   컬럼1 타입 옵션,
   컬럼2 타입 옵션,
   CONSTRAINT 제약명 PRIMARY KEY(컬럼),
   CONSTRAINT 제약명 FORIEGN KEY(컬럼) REFERENCES 테이블명(기본키명)
   )
   - 옵션 : PRIMARY KEY, NOT NULL, UNIQUE, DEFALUT 초기값 
           AUTO_INCREMENT(기본키에만) 
   - CONSTRAINT 제약명 은 생략가능. 
     자동으로 만들어서 추가 됨. 
 - DROP DATABASE IF EXISTS DB명;
 - DROP TABLE IF EXISTS 테이블명;
 
DML (3번 파일)
 - 데이터 추가, 수정, 삭제, 조회(CRUD) 
 - INSERT, UPDATE, DELETE, SELECT 
 - INSERT INTO 테이블(컬럼1, 컬럼2, ..., 컬럼N) VALUES(값1, 값2, ..., 값N)
   - 생략된 컬럼에는 NULL허용이면 NULL이 들어감.
   - NOT NULL이면 DEFAULT값이 들어감. => DEFAULT 값을 설정하지 않으면 에러 발생. 
   - 기본키이고 AUTO_INCREMENT이면 다음 숫자가 자동으로 들어감 
 - UPDATE 테이블명 SET 컬럼1 = 값1, 컬럼2 = 값2, ... WHERE 조건;
 - DELETE FROM 테이블명 WHERE 조건; 
 - SELECT (4번 파일)
	 SELECT * FROM 테이블
	 WHERE 조건 
	 GROUP BY 컬럼1 ASC|DESC , 컬럼2 ASC|DESC 
	 HAVING 조건 
	 ORDER BY 정렬 
	 LIMIT 번지, 개수;
   - 실행 순서
     FROM 테이블 
     > WHERE 조건 
     > GROUP BY 
     > HAVING 
     > SELECT * 
      (단, 집계함수인 경우 MYSQL과 오라클은 편의를 위해 별칭을 다른곳에서 사용할수 있음)
     > ORDER BY 
     > LIMIT 
   - 서브 쿼리 
     - 쿼리 안에 쿼리가 들어감 
     - 서브쿼리는 SELECT 
 
DCL (7번 파일)
 - 사용자 추가/삭제, 권한 부여/회수 
 - CREATE, DROP, GRANT, REVOKE 
 - CREATE USER '아이디'@'호스트' IDENTIFIED '비번';
   - 호스트 : 접근 권한. LOCALHOST : 내부에서만, % : 외부에서 접근 가능 
 - DROP USER '아이디'@'호스트'; 
 - GRANT 권한1,권한2, ... ON DB명.테이블명 TO '아이디'@'호스트';
 - REVOKE 권한1, 권한2, ... ON DB명.테이블명 FROM '아이디'@'호스트';
 
TCL (8번 파일)
 - 트랜젝션 제어. 트랜잭션 시작, 저장, 완료, 취소 
 - 프로시저, 트리거, 이벤트 스케줄러에서 사용하면 좋음(강력 추천) 
 - START TRANSACTION;
 - SAVEPOINT 저장명; 
 - COMMIT;
 - ROLLBACK;
 - ROLLBACK TO 저장명; 
*/
# 내장 함수 : DBMS가 제공하는 함수 (5번 파일)
# - 조건 함수 
# - 문자열 함수 
# - 날짜 함수 

# 윈도우 함수 : 순위를 매기는 함수 (6번 파일)
# - ROW_NUMBER : 1,2,3,4,5
# - RANK : 1,2,2,4,5
# - DENSE_RANK : 1,2,2,3,4
# - NTILE(N) : 그룹화 - N개의 그룹을 만들어서 순위를 붙임 

# 프로시저 (9번 파일)
# - 일련의 기능을 하도록 모아놓은 쿼리 
# - CALL을 통해 프로시저를 원할 때 호출 

# 트리거 (10번 파일)
# - 이벤트가 발생했을 때 실행해야 하는 작업을 모아놓은 쿼리 
# - 이벤트가 발생했을 때 자동으로 실행 

# 스케쥴러 (11번 파일)
# - 주기적으로 실행해야 하는 코드를 예약하거나 특정 시간에 한번만 실행해야 하는 코드를 예약
#   할 때 사용 














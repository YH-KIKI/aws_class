/*
데이터
- 정보를 나타내는 원시적인 사실이나 값의 집합

정보
- 의사결정에 사용될 수 있는 의미 있는 데이터

데이터베이스
- 체계적으로 구조화된 데이터 집합

DB 정의
- 공용데이터 : 공동으로 사용
- 통합데이터 : 중복을 최소화
- 저장데이터 : 컴퓨터에 저장
- 운영데이터 : 조식 업무를 수행하는데 필수적인 데이터

관계형 데이터베이스
- 데이터를 테이블 형태로 표현하고 테이블 사이의 관계를 도출하는 구조

관계형 데이터베이스 설계 및 구축 과정
1. 사용자 요구 도출/정의
2. 개념적 모델 정의(ERD)
3. 논리적 모델 정의 : 릴레이션을 정의하고, 릴레이션의 속성들을 정의
4. 물리적 모델 정의 : 릴레이션을 테이블로 변경해서, 속성들의 타입과 제약조건을 정의
5. DB 구조 정의 : 실제 DB 생성 및 테이블 생성

슈퍼키
- 테이블에서 행을 유일하게(최대 1개로) 식별(검색)할 수 있는 하나의 속성 또는 속성의 집합

후보키
- 슈퍼키들 중에서 불필요한 속성을 제거한 키
- 행을 유일하게 식별할 수 있는 속성의 최소 집합

기본키
- 후보키 중 대표로 선정된 키
- 의미상 대표할 수 있는 속성이 좋음
	- 대학생 테이블에서 주민번호보단 학번이 의미상 기본키에 적절
- UNIQUE  + NOT NULL이 합쳐짐

대체키
- 기본키로 선정되지 못한 후보키
- UNIQUE의 특징을 가짐

외래키
- 다른 테이블을 참조하는 속성
- 다른 테이블의 기본키를 참조

복합키
- 기본키가 여러 속성으로 이루어진 키
- 복합키가 기본키인 테이블을 참조하면 참조하는 테이블의 외래키는 복합키 개수만큼 추가됨

대리키
- 복합키이거나 마땅히 기본키로 설정할 속성이 없을 때 사용하는 키
- 정수로 하고 AUTO_INCREAMENT를 이용하면 기본키를 쉽게 관리

개체 무결성
- 기본키 제약 조건
- 기본키는 NULL을 가질 수 없고, 중복되면 안됨

참조 무결성
- 외래키 제약 조건
- 외래키키의 값은 참조하는 테이블의 기본키에 있는 값이거나 NULL이어야 함

타입
- 문자
  - CHAR(N) : 고정 길이, 최대 N자
  - VARCHAR(N) : 가변 길이, 최대 N자
  - TEXT : 긴 문자
  - BLOB : 바이너리 데이터
  - ENUM(A,B,C) : 열거형, 지정된 값 중 하나, A,B,C중 하나만 넣음
- 정수
  - INT, BIGINT
- 실수
  - DOUBLE, FLOAT
- 날짜
  - DATE, DATETIME
  
SQL
 - 구조회된 질의 언어
 - 관계형 데이터베이스에서 데이터를 관리하고 조작하기 위해 표준화된 언어
 - SQL은 표준이나, DBMS마다 문법이 조금씩 다름
 - DDL, DML, DCL, TCL
  
DDL
 - 데이터 정의어
 - DB, 테이블 생성, 수저으 삭제
 - CREATE, DROP, ALTER
  
DML
 - 데이터 조작어
 - 데이터를 추가, 수정, 삭제
 - INSERT 
	- 데이터 추가
    - INSERT VALUES : 모든 값을 직접 입력
    - INSERT SELECT : 검색 결과를 활용하여 추가
 - UPDATE
	- 데이터 수정
 - DELETE
	- 데이터를 삭제
    - AI값이 초기화 되진 않음
 - SELECT
    - 데이터 조회
    - 문법
SELECT * FROM 테이블A
  [INNER | LEFT | RIGHT] JOIN 테이블B ON 테이블A.컬럼1 = 테이블B.컬럼2
  WHERE 조건
  GROUP BY 컬럼1, ...
  HAVING 조건
  ORDER BY 컬럼 [ASC | DESC], ...
  LIMIT 번지, 개수
- 실행순서
  1. FROM 테이블
  2. WHERE 조건
  3. GROUP BY 컬럼, ...
  4. HAVING 조건
  5. SELECT * (MYSQL과 오라클에서 예외적으로 집계함수의 별명이 있는 경우 4번(HAVING절)보다 집계함수 별명이 먼저 실행)
  6. ORDER BY
  7. LIMIT
- 집계함수
  - GROUP BY 후 개수, 평균, 합등을 계산할 때 사용
  - COUNT, SUM, AVG, MAX, MIN
- 내장함수
  - DBMS에서 제공하는 기능
  - 문자열 관련
  - 날짜 관련
  - 조건문 관련
- JOIN
  - 두 개이상 테이블을 연결할 때 사용
  - INNER JOIN
	- 두 테이블에 데이터가 이쓴ㄴ 행들만 연결
  - OUTER JOIN 
	- 한 테이블을 기준으로 연결. 데이터가 없으면 없는대로 연결
    - LEFT : 왼쪽 테이블 기준
    - RIGHT : 오른쪽 테이블 기준
- 서브쿼리
  - 쿼리 안에 쿼리가 들어가는 것
  - 서브쿼리는 SELECT문
- 윈도우 함수
  - 순위를 정하는 함수
  - ROW_NUMBER
	- 중복된 값도 다른 순위로 판별
    - 1,2,3,4,5
  - RANK
	- 중복된 값은 같은 순위로 판별
    - 1,2,2,4,5
  - DENSE_RANK
	- 중복된 값은 같은 순위로 판별
    - 1,2,2,3,4
  - NTILE
	- 지정된 개수의 그룹으로 나눔
- DCL
  - 데이터 제어어
  - 계정 생성/삭제, 권한 부여/회수
  - CREATE, DROP, GRANT, REVOKE
- TCL
  - 트랜잭션 제어어
  - 트랜잭션 시작, 저장, 롤백 
  - START TRANSACTION, SAVEPOINT, ROLLBACK

저장 프로시저
- 일련의 작업을 하도록 모아놓은 쿼리의 집합
- 장점
  - 하나의 요청으로 여러 SQL문을 실행
  - 처리 시간이 줄어듬
  - 참조 무결성 유지가 가능 
- 단점
  - 재사용성이 나쁨
  - 업무 사양 변경 시 프로시저의 정의를 변경
- 용어
  - DELIMITER
	- 문장 끝을 나타내는 기호를 설정
  - IN : 프로시저의 매개변수로 넘겨 주는 값
  - OUT : 프로시저의 매개변수로 넘겨 받는 값
  - INOUT : 프로시저의 매개변수로 넘겨주고 난 뒤 넘겨 받는 값
  - DELCARE
	- 변수 선언 시 사용
    - BEGIN 바로 아래에 모아 놓아야 함
  - SET : =을 통해 변수 값 수정할 때 사용
- IF문 
IF 조건식 THEN 
	실행문;
ELSEIF 조건식 THEN
	실행문;
ELSE
	실행문;
END IF;

- CASE문1
CASE
	WHEN 조건식 THEN
		실행문;
	WHEN 조건식 THEN
		실행문;
	ELSE
		실행문;
END CASE;

- CASE문2
CASE 변수
	WHEN 값 THEN
		실행문;
	WHEN 값 THEN
		실행문;
	ELSE 
		실행문;
END CASE;

- WHILE 문
WHILE 조건식 DO
	실행문
END WHILE;

- REPEAT 문
REPEAT
	실행문;
UNTIL 조건식
END REPEAT;

트리거
- 이벤트가 발생했을 때 처음으로 실행되는 작업
- 목적 : 데이터 무결성을 지키기 위해
- OLD : 이벤트 발생 전 데이터 객체. DELETE, UPDATE
- NEW : 이벤트 발생 후 데이터 객체. INSERT, UPDATE
- 이벤트가 발생한 테이블을 수정할 수 있음

이벤트 스케줄러
- 작업이 지정된 시간에 실행되도록 하는 것
- 규칙적인 작업을 할 때 사용
- 지정된 시간에 작업할 때 사용

뷰
- 사용자에게 일반 테이블과 똑같이 보이는 가상 테이블
- 뷰를 생성 -> 물리적으로 테이블을 만들지 않음
- 왜?
  - 보안, 편의성, 독립성
  
정규화
- 데이터 중복을 최소화하여 무결성을 유지하기 위해 데이터를 구조화 하는 것
  -> 테이블 쪼개기
- 제1정규화
  - 값이 원자값이 되도록 테이블로 나눔
- 제2정규화
  - 부분함수 종속 제거
  - 기본키가 복합키이고,
	컬럼이 기본키에 의해 결정되지 않고,
    기본키 일부에 의해 결정될 때 적용
- 제3정규화
  - 이행적 함수 종속 제거
  - 기본키가 아닌 컬럼이 다른 컬럼을 종속되게 할 때 적용
- BCNF
  - 복합키 내에서 종속 관계일 때 적용
*/
# TRUNCATE VS DROP VS DELETE 자리를 확인하는 예제
DROP DATABASE IF EXISTS TEST;
CREATE DATABASE TEST;
USE TEST;

CREATE TABLE TEST1(
	NUM INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(10)
);
CREATE TABLE TEST2(
	NUM INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(10)
);
CREATE TABLE TEST3(
	NUM INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(10)
);

INSERT INTO TEST1(NAME) VALUES('TEST1'), ('TEST2');
INSERT INTO TEST2(NAME) VALUES('TEST1'), ('TEST2');
INSERT INTO TEST2(NAME) VALUES('TEST1'), ('TEST2');


# TEST1 테이블 초기화
TRUNCATE TABLE TEST1;
# TEST2 테이블 삭제
DROP TABLE TEST2;
# TEST3 테이블 삭제 
DELETE FROM TEST3;

INSERT INTO TEST1(NAME) VALUES("TEST1"), ("TEST2");
INSERT INTO TEST3(NAME) VALUES("TEST1"), ("TEST2");


SELECT * FROM TEST1;
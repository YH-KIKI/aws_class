/*
키
- 기본키 : 검색 결과가 항상 최대 1행이 조회되게 하는 속성들의 집합
	- 예 : 주민번호, 학번
    - 개체 무결성 : 기본키 is not null. 중복 X
- 외래키 : 다른 테이블을 참조할 때 사용하는 속성
	- 다른 테이블의 기본키 값을 가짐
    - 참조 무결성 : 외래키의 값이 다른 테이블 기본키의 있는 값이거나 null이어야 함
    
SQL
- 구조화된 질의 언어
- DDL, DML, DCL, TCL
- DDL : 테이블, DB추가 및 제거
- DML : 테이터 추가/수정/삭제/조회 -> CRUD
- DCL : 사용자 추가 및 권한 부여
- TCL : 트랜젝션 제어

DDL
- create : 추가
- drop : 삭제
- alter : 수정
DML
- insert : 데이터 추가
	- insert values
    - insert select
- update : 데이터 수정 
	- set
- delete : 데이터 삭제
- select : 데이터 조회
select * from
where
	조건식
order by 컬럼1 방법
limit 번지, 개수;

집에서 데이터 세팅
1. MySQL 설치
2. DDL예제 전체 실행 (전체 실행 단축키 - 컨 + 시프트 + 엔터)
3. DML 예제 전체 실행
4. DML_SELECT 결과 확인하고 싶은 쿼리 실행 (컨 + 엔터로 하나씩 실행해보기)
*/
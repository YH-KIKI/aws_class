# TCL
# - 트랜잭션 제어 언어
# - 트랜잭션 : DB 상태를 변화 시키기 위해 수행하는 작업 단위
# - 특징 : 원자성(Atomicity), 일관성(Consistency), 
#		  고립성(Isolation), 영속성(Durability)
#	- 원자성 : 트랜잭션 작업은 전부 성공하거나 전부 실패하여 취소 되어야 함
#   - 일관성 : 트랜잭션이 성공적으로 실행되면, 언제나 일관성 있는 상태로 유지 되어야 함
# 	- 고립성 : 트랜잭션이 실행되는 동안에 다른 트랜잭션이 현재 작업에 껴들 수 없음
# 	- 영속성 : 성공적으로 완료된 트랜잭션의 결과는 시스템 장애가 발생해도 영구적으로 반영되어야 함

# MySQL은 자동 커밋이 설정 되어 있어서 우리가 작성한 쿼리들이 바로 커밋됨

# START TRANSACTION/BEGIN/BEGIN WORK : 트랜잭션 시작
# ROLLBACK : 트랜잭션 시작 전상태로 되돌림
# ROLLBACK TO 저장명 : 저장명 전상태로 되돌림
# SAVEPOINT 저장명 : 임시로 저장할 위치를 지정
# COMMIT : 지금까지 했던 작업을 반영
START TRANSACTION;

USE SHOPPINGMALL2;

INSERT INTO CART(ID, AMOUNT, CODE) VALUES('abc123', 2, 'ACC002');

SELECT * FROM CART;

ROLLBACK; 

START TRANSACTION;
INSERT INTO CART(ID, AMOUNT, CODE) VALUES('abc123', 2, 'ACC002');
SAVEPOINT INSERT_CART;
UPDATE CART SET AMOUNT = 3 WHERE ID = 'abc123' AND CODE = 'ACC002';
ROLLBACK TO INSERT_CART;
COMMIT;
ROLLBACK; # COMMIT후 ROLLBACK을 해도 추가된 데이터가 사라지지 않음
SELECT * FROM CART;

# 트리거 
# - 테이블에 대한 이벤트(insert, update, delete)에 반응해 자동으로 실행되는 작업 
# - A테이블에 insert 트리거를 등록하면, A테이블에 데이터가 추가되어(insert) 이벤트가
#   발생하면 트리거가 동작 (이벤트 발생한 테이블의 데이터를 수정할 수 없음)
# - 트리거를 통해 데이터 무결성을 지킬수 있음 

USE SHOPPINGMALL2;
# 트리거 확인
SHOW TRIGGERS;

# 트리거 삭제 
# DROP TRIGGER [IF EXISTS] 트리거명;

# 트리거 정의
/*
DELIMITER 기호
CREATE TRIGGER 트리거명 
트리거동작시간 트리거이벤트 ON 테이블 
FOR EACH ROW 
BEGIN
	구현;
END 기호 
DELIMITER ; 

- 트리거동작시간 
  - 트리거가 동작되는 시점 
  - BEFORE | AFTER 
- 트리거이벤트
  - 트리거가 실행되는 이벤트 
  - INSERT, UPDATE, DELETE 
- 구현
  - OLD와 NEW를 통해 한 행을 선택 
  - OLD : 예전 데이터. DELETE : 삭제하려는 데이터, UPDATE : 수정하기전 데이터 
  - NEW : 새 데이터. INSERT : 추가된 데이터, UPDATE : 수정된 데이터 
  - OLD.컬럼명(또는 NEW.컬럼명) 을 통해 접근,
*/

# 제품을 구매하면 제품 제고량이 구매 수량만큼 빠지는 트리거 
DROP TRIGGER IF EXISTS BUY_INSERT;
DELIMITER $$
CREATE TRIGGER BUY_INSERT
AFTER INSERT ON BUY
FOR EACH ROW
BEGIN
	# 제품 수량을 변경 
    UPDATE PRODUCT 
	SET
		AMOUNT = AMOUNT - /*구매한수량*/ NEW.AMOUNT
	WHERE
		CODE = /*구매제품코드*/ NEW.CODE;
END $$
DELIMITER ;

# 트리거 테스트할 때 트리거가 잘못 작성될 수 있어서 트랜잭션으로 테스트 
START TRANSACTION; 

INSERT INTO BUY(ADDRESS, AMOUNT, CODE, PRICE, ID)
VALUES("독도", 3, "ACC004", 240000, "abc123");


SELECT * FROM PRODUCT;

# 위 INSERT에서 트리거에서 에러가 나면 ROLLBACK으로 되돌리고,
# 안나고 정상 결과가 나오면 COMMIT으로 마무리 
COMMIT;

# 트리거 VS 프로시저 
# - 트리거 이벤트는 이벤트가 발생된 테이블의 데이터를 수정할 수 없음 
# - BUY 테이블에 INSERT 트리거를 등록 한 후, 트리거에서 BUY테이블을 수정하려고 하면 안됨 
#   - 구매할 때 구매 총 가격을 자동으로 계산하기 위해 트리거를 이용하려 하면 안됨 
#   - 이벤트가 발생한 테이블이 BUY이고, 총가격을 수정할 테이블도 BUY이기 때문에.
#   - 이런 경우는 프로시저를 이용 

SHOW TRIGGERS;













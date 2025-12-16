# 프로시저
# - 일련의 쿼리를 하나의 함수처럼 실행하기 위한 쿼리의 집합
# - 장바구니에 있는 제품 여러개를 구매
# 	- > 제품 재고량 변경
#	- > 구매한 제품을 장바구니에서 삭제

# 프로시저 목록 확인
SHOW PROCEDURE STATUS;

# 프로시저 스크립트 확인
# SAKILA DB에 있는 FILM_IN_STOCK 프로시저 확인
USE SAKILA;
SHOW CREATE PROCEDURE FILM_IN_STOCK;
# 위 결과에서 CREATE PROCEDURE에 있는 내용 확인
# - 해당 칸 우클릭 Copy Fleld를 클릭하면 내용이 복사됨
# - 다른 곳에서 붙여서 확인

# 프로시저 삭제
DROP PROCEDURE IF EXISTS 프로시저명; 

/*
DELIMITER 기호	# 프로시저를 정의하는 동안에 문장의 끝이 ;이 안되도록 변경
CREATE PROCEDURE 프로시저명(
	[IN | OUT | INOUT | 변수명 타입,]
)
BEGIN
	프로시저 구현;
END 기호	
DELIMITER; 		# 프로시저를 정의하는 동안에 바꿀 기호를 다시 ;으로 바꿈

DELIMITER
- 문장(쿼리)의 끝을 나타내는 기호
- 기본은 ; 
- DELIMITER를 이용하여 문장의 끝 기호를 변경할 수 있음

IN
- 프로시저 호출시 필요한 정보가 있을 때 사용
OUT 
- 프로시저가 끝날 때 알려줄 정보가 있을 때 사용
INOUT
- 프로시저가 호출 시 필요한 정보가 끝날 때 알려줄 정보로 바뀔 때 사용 
*/

# 정의한 프로시저 호출
# CALL 프로시저명(값|변수들);

#프로시저 삭제
DROP PROCEDURE IF EXISTS CURRENT_TIME_NOW;
# 현재시간의 년, 월, 일을 조회하는 프로시저를 생성
DELIMITER $$	
CREATE PROCEDURE CURRENT_TIME_NOW()
BEGIN
	SELECT NOW() 현재시간;
END $$
DELIMITER ;

CALL CURRENT_TIME_NOW();

# 000 숫자에서 3자리가 아니면 앞에 0 붙이기
DROP PROCEDURE IF EXISTS LPAD_NUMBER;
DELIMITER $$	
CREATE PROCEDURE LPAD_NUMBER(
	IN NUM INT 
)
BEGIN
	SELECT LPAD(NUM, 3, '0') LPAD_NUMBER;
END $$
DELIMITER ;

CALL LPAD_NUMBER(1);

# 변수 선언 
# SET @변수명 = 값;
SET @NUM = 20;
CALL LPAD_NUMBER(@NUM);

DROP PROCEDURE IF EXISTS PLUS;
DELIMITER $$	
CREATE PROCEDURE PLUS(
	IN NUM INT,
    OUT RES INT
)
BEGIN
	SET RES = NUM + 1;
END $$
DELIMITER ;

CALL PLUS(1, @NUM);
SELECT @NUM;

USE SHOPPINGMALL2;
# 장바구니
DROP PROCEDURE IF EXISTS BUY_CART;
DELIMITER $$	
CREATE PROCEDURE BUY_CART(
    IN CART_NUM INT,
    IN ADDRESS VARCHAR(100)
)
BEGIN
	# BEGIN과 END 사이에서 사용하는 변수는 DECLARE로 선언하고, 시작 위치에 모아 놓아야 함
    DECLARE _PRICE INT;
    DECLARE _AMOUNT INT;
    DECLARE _CODE CHAR(6);
    DECLARE _ID VARCHAR(13);
    
    # SQL예외가 발생하면 롤백 하는 기능
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    
    # 트랜잭션 시작
    START TRANSACTION;
    
    SET _AMOUNT = (SELECT AMOUNT FROM CART WHERE NUM = CART_NUM);
    SET _CODE = (SELECT CODE FROM CART WHERE NUM = CART_NUM);
    SET _ID = (SELECT ID FROM CART WHERE NUM = CART_NUM);
    SET _PRICE = (SELECT PRICE FROM PRODUCT WHERE CODE = _CODE);
    
    # BUY 테이블에 구매 내역 추가
    INSERT INTO BUY(ADDRESS, AMOUNT, CODE, PRICE, ID)
		VALUES(ADDRESS, _AMOUNT, _CODE, _PRICE*_AMOUNT, _ID);
    # PRODUCT 테이블에 재고량 수정
    UPDATE PRODUCT
    SET
		AMOUNT = AMOUNT - _AMOUNT
	WHERE
		CODE = _CODE;
  
    # CART 테이블에서 해당 장바구니 삭제
    DELETE FROM CART WHERE NUM = CART_NUM;
    COMMIT;
END $$
DELIMITER ;

CALL BUY_CART(3, '경기도');
CALL BUY_CART(5, '제주도');
# ACC001을 1개 구매
# ACC001 재고가 8개로 변경
# 장바구니 3번이 삭제

# ACC002을 2개 구매
# ACC002 재고가 7개로 변경
# 장바구니 5번이 삭제


/*
프로시저에서 사용하는 문법
1. 변수 선언
- 변수 선언은 BEGIN 밑에 모아 놓음
- 선언 방법
  DECLART 변수명 타입 [DEFAULT 초기값];
2. 변수값 수정
- =은 기본 비교연산이기 때문에 SET을 이용하여 수정
  SET 변수명 = 값;
  SET 변수명 = (SELECT 쿼리);
3. 조건문 IF
 IF 조건식 THEN
   실행문;
 ELSEIF 조건식 THEN
   실행문;
 ELSE
   실행문;
 END IF;
4. 조건문 CASE
 CASE 변수
   WHEN 값 THEN
	  실행문;
   WHEN 값2 THEN
      실행문;
   ELSE
      실행문;
   END CASE;
5. 반복문 WHILE
 WHILE 조건식 DO
   실행문;
 END WHILE;
6. 반복문 REPEAT
 REPEAT 
   실행문;
 UNTIL 조건식
 END REPEAT;
7. 반복문 CURSOR
 - 검색 결과를 반복문으로 활용할 때 사용
 DECLARE 변수A BOOLEAN DEFAULT FALSE; # 반복을 멈출지 말지를 결정하는 변수
 DECLARE 커서 CURSOR FOR SELECT 컬럼1, 컬럼2, ... FROM 테이블명 [WHERE절];
 # 더이상 할 내용이 없으면 변수A를 TRUE로 변경
 DECLART CONTINUE HANDLER FOR NOT FOUND SET 변수A = TRUE;
 OPEN 커서;
 루프 : LOOP
 FETCH 커서 INTO 변수1, 변수2, ...;
 IF 변수A THEN
	LEAVE 루프;
 END IF;
 실행문;
 END LOOP;
 CLOSE 커서;
*/

# CURSOR 사용하기
DROP PROCEDURE IF EXISTS PRINT_PRODUCT;
DELIMITER $$
CREATE PROCEDURE PRINT_PRODUCT()
BEGIN
	DECLARE _DONE BOOLEAN DEFAULT FALSE; # 커서를 멈추기 위한 변수
    DECLARE _TITLE VARCHAR(50); # 제목
    DECLARE _PRICE INT; # 가격
    
    DECLARE _CURSOR CURSOR FOR SELECT TITLE, PRICE FROM PRODUCT;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET _DONE = TRUE;
    
    OPEN _CURSOR;
    PRODUCT_LOOP : LOOP
		# 커서에서 꺼내서 _TITLE, _PRICE에 넣어줌
		FETCH _CURSOR INTO _TITLE, _PRICE;
        # 꺼내올게 없으면 루프 종료
        IF _DONE THEN
			LEAVE PRODUCT_LOOP;
		END IF;
        SELECT _TITLE, _PRICE; 
    END LOOP;
    CLOSE _CURSOR;
END $$
DELIMITER ;

CALL PRINT_PRODUCT();




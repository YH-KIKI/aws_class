use shoppingmall1;
# 다음 회원을 추가
# 아이디 : abc123, 비번 abc123, 이메일 : abc123@naver.com, 번호 : 011-1111-2222
insert into user(id, pw, email, phone)
values("abc123", "abc123", "abc123@naver.com", "011-1111-2222");

# 제품 분류 추가
# 제품 분류 코드 ACC, 분류명 : 악세서리
# 제품 추가
# 악세서리 - 제품명 : 18K목걸이, 내용 : 예쁜 목걸이입니다. 가격 : 100000, 썸네일 없음
insert into product(code, ca_code, ca_title, title, content, price)
values("ACC001", "ACC", "악세서리", "18K목걸이", "예쁜 목걸이 입니다.", 100000);

# 악세서리 - 제품명 : 18K반지, 내용 : 예쁜 목걸이입니다. 가격 : 100000, 썸네일 없음
insert into product(code, ca_code, ca_title, title, content, price)
values("ACC002", "ACC", "악세서리", "18K반지", "예쁜 반지 입니다.", 100000);

# abc123회원이 ACC001를 2개 장바구니에 담음
insert into cart(amount, id, code)
values(2, "abc123", "ACC001");

# abc123회원이 ACC001을 1개 장바구니에 담음
update cart set amount =1 where id= "abc123" and code = "ACC001";

# abc123회원이 ACC002을 1개 장바구니에 담음 
insert into cart(amount, id, code)
values(1, "abc123", "ACC002");

# 제품 입고(제품 수량 추가)
update product set amount = 10 where code = "ACC001";
update product set amount = 10 where code = "ACC002";

# abc123회원이 장바구니에 있는 모든 제품들을 구매 주소 : 서울시
insert into buy(amount, address, id, code)
values(1, "서울시", "abc123", "ACC001"),
(1, "서울시", "abc123", "ACC002");

# 장바구니 목록에서 구매한 제품들 삭제
delete from cart where id = "abc123" and code = "ACC001";
delete from cart where id = "abc123" and code = "ACC002";

# 수량 감소
update product set amount = aount -1 where code = "ACC001";
update product set amount = aount -1 where code = "ACC002";

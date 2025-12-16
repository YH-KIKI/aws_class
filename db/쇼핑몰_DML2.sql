use shoppingmall2;
# 다음 회원을 추가 
# 아이디 : abc123, 비번 abc123, 이메일 : abc123@naver.com 번호 : 011-1111-2222
insert into user(id, pw, email, phone)
values("abc123", "abc123", "abc123@naver.com", "011-1111-2222");

# 제품 분류 추가 
# 제품 분류 코드 : ACC, 분류명 : 악세서리 
# 제품 분류 코드 : TOP, 분류명 : 상의 
# 제품 분류 코드 : PAN, 분류명 : 바지 
insert into category(code, title) 
values("ACC", "악세서리"), ("TOP", "상의"), ("PAN", "바지");

# 제품 추가 
# 악세서리 -  제품명 : 18K목걸이, 내용 : 예쁜 목걸이입니다. 가격 : 100000, 썸네일 없음 
insert into product(code, title, content, price, num)
select "ACC001", "18K목걸이", "예쁜 목걸이입니다.", 100000, num 
from category where title = "악세서리";


# 악세서리 -  제품명 : 18K반지, 내용 : 예쁜 반지입니다. 가격 : 100000, 썸네일 없음 
insert into product(code, title, content, price, num)
select "ACC002", "18K반지", "예쁜 반지입니다.", 100000, num 
from category where title = "악세서리";

# abc123회원이 ACC001을 2개 장바구니에 담음 
insert into cart(amount, id, code)
values(2, "abc123", "ACC001");

# abc123회원이 ACC001을 1개 장바구니에 담음 
update cart set amount = 1 where id ="abc123" and code = "ACC001";

# abc123회원이 ACC002를 1개 장바구니에 담음
insert into cart(amount, id, code)
values(1, "abc123", "ACC002");

# 제품 입고(제품 수량 추가) 
update product set amount = 10 where code = "ACC001";
update product set amount = 10 where code = "ACC002";

# abc123회원이 장바구니에 있는 모든 제품들을 구매 주소 : 서울시 
insert into buy(amount ,address, id, code, price)
select cart.amount, "서울시", id, code, price * cart.amount from cart 
join product using(code)
where id = "abc123";


# 장바구니 목록에서 구매한 제품들 삭제 
delete from cart where id = "abc123" and code = "ACC001";
delete from cart where id = "abc123" and code = "ACC002";

# 수량 감소 
update product set amount = amount - 1 where code = "ACC001";
update product set amount = amount - 1 where code = "ACC002";

# "abc123"회원이 ACC001 제품을 1개 장바구니에 담음
insert into cart(id, amount, code) values("abc123", 1, "ACC001");

# qweqwe회원 가입 
insert into user(id, pw, email, phone) 
values("qweqew", "qweqwe", "qweqwe@naver.com", "011-2233-4444");

# 악세서리 제품들 추가
insert into product(code, title, content, amount, price, num)
values
("ACC003", "반짝이는 귀걸이", "", 10, 50000, 1),
("ACC004", "14K 팔찌", "", 10, 80000, 1),
("ACC005", "순금 목걸이", "", 10, 150000, 1),
("ACC006", "순금 반지", "", 10, 250000, 1);

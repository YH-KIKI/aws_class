# 분류가 악세서리인 제품을 조회
select * from product where ca_title = "악세서리";

# 제품명에 18K가 포함된 제품을 조회
select * from product where title like "%18K%";

# abc123회원의 장바구니 목록
select * from cart where id = "abc123";

# abc회원이 구매한 구매 내역 조회
select product.*, buy.amount 구매수량 from buy
join product using(code)
where id = "abc123";

# acb123회원의 누적 금액을 조회
select id, sum(price * buy.amount) 누적금액
from buy join product using(code)
where id = "abc123";

# 회원별 누적 금액을 조회
select id, sum(price * buy.amount) 누적금액
from buy join product using(code)
group by id;


# DB 삭제
drop database if exists shoppingmall1;

# DB 추가
create database if not exists shoppingmall1;

# DB 선택
use shoppingmall1;

# 회원(아이디, 비번, 이메일, 전화번호) 테이블 삭제 및 추가
drop table if exists user; 

create table if not exists user(
	id varchar(13) primary key, # 아이디 6~13자
    pw varchar(20), # 비번 6~20자
    email varchar(50) unique, # 이메일 최대 50자
    phone varchar(13) unique #000-0000-0000
);

# 제품(제품코드, 분류코드, 분류명, 제품명, 제품상세, 제품가격, 썸네일, 제품수량)
drop table if exists product;

create table if not exists product(
	code char(6) primary key,
	ca_code char(3) not null, 
    ca_title varchar(10) not null,
    title varchar(100) not null,
    content longtext not null,
    price int not null default 0,
    thumb varchar(255),
    amount int not null default 0
);

# 장바구니(장바구니번호, 수량, 아이디(FK), 제품코드(FK))
drop table if exists cart;

create table if not exists cart(
	num int primary key auto_increment,
    amount int not null,
    id varchar(13) not null,
    code char(6) not null,
    constraint fk_ca_us foreign key(id) references user(id),
    constraint fk_ca_pr foreign key(code) references product(code)
);

# 구매(구매번호, 주소, 수량, 아이디(FK), 제품코드(FK))
drop table if exists buy;

create table if not exists buy(
	num int primary key auto_increment,
    amount int not null,
    address varchar(100) not null,
    id varchar(13) not null,
    code char(6) not null,
    constraint fk_bu_us foreign key(id) references user(id),
    constraint fk_bu_pr foreign key(code) references product(code)
);

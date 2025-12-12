# DDL : DB/테이블 추가, 수정, 삭제

# DB 생성
# create database [if not exists] DB명;

# world DB가 이미 있는 경우 에러가 발생해서 이후 쿼리가 실행되지 않을 수 있음
# Error Code: 1007. Can't reate database 'world'; database esists
# create database world; # 에러 발생

# if not exists를 붙이면 DB가 있는 경우 생성하지 않기 때문에 에러가 발생하지 않음
# 이후 코드가 정상 실행
# create database if not exists world;
create database if not exists sample;

# DB삭제
# drop database [if exists] db명;
drop database if exists sample;

create database if not exists sample; 

use sample;

# 테이블 생성
/*
CREATE TABLE [IF NOT EXISTS] 테이블명(
	컬럼명 타입	[ZEROFILL] [UNIQUE] [NOT NULL] [DEFAULT 기본값] [PRIMARY KEY][AUTO_INCREMENT] ,
	컬럼명 타입	[ZEROFILL] [UNIQUE] [NOT NULL] [DEFAULT 기본값],
	제약조건,
	CONSTRAINT 제약조건명 PRIMARY KEY(컬럼명),
	CONSTRAINT 제약조건명 FOREIGN KEY(컬럼명) REFERENCES 참조테이블(기본키명),
	CONSTRAINT CHECK(논리식)
;
*/
# 테이블에서 속성 이름은 한글도 가능하지만 가능하면 영어로 하는게 좋음
# 학생(학생고유번호, 학년, 반, 번호, 이름) 
create table if not exists student(
	code int primary key auto_increment,
    grade int not null default 1,
    class int not null default 1,
    num int not null default 1,
    name varchar(10)
);
# 과목(과목고유번호, 학년, 학기, 과목명)
create table if not exists subject(
	code int primary key auto_increment,
    grade int not null default 1,
    semester int not null default 1,
    title varchar(20) not null
);
# 성적(성적교유번호, 학생고유번호, 과목고유번호, 성적)
create table if not exists score(
	code int primary key auto_increment,
    st_code int not null,
    sj_code int not null,
    score int not null default 0,
    # st_code 컬럼이 student 테이블에 code를 참조
    foreign key(st_code) references student(code),
    # st_code 컬럼이 subject 테이블에 code를 참조
    foreign key(sj_code) references subject(code)
);


# 테이블 삭제
# drop table if exists 테이블명;
# drop table if exists score; 	# 외래키를 가지고 있기 때문에 먼저 삭제
# drop table if exists student;
# drop table if exists subject;

# 학생 테이블에 학생 설명을 관리하는 컬럼을 추가
alter table student add etc varchar(100);

# 학생 설명 관리하는 컬럼을 100자에서 200자로 수정
alter table student change etc etc varchar(200);

# 학생 설명 관련하는 컬럼을 삭제
alter table student drop etc;

# 테이블을 초기화 할 때 사용
# 데이터들 모두를 제거, auto_increment값을 1로 초기화
# truncate table 테이블명; 

# truncate vs drop 테이블 vs delete
# truncate : 데이터 삭제. 테이블 남음. auto_increment 1로 초기화
# drop : 테이블 삭제. 당연히 데이터 삭제alter
# delete : 데이터 삭제. auto_increment는 기존 값 유지

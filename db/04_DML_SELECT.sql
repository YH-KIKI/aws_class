# 검색 : select

# 테이블 전체 검색
# select 컬럼1, 컬럼2, ... from 테이블명; # 내가 입력한 컬럼만 조회
# select * from 테이블명; # 모든 컬럼 조회
select grade, class, num, name from student;
select * from student;

# 컬럼명에 별명 붙이기
# select 컬럼1 as 별명1, 컬럼2 별명2, ... from 테이블명 #(1처럼 as넣어도 되고 2처럼 공백해도 되고)
select grade as 학년, class 반, num 번, name 이름 from student;

# 조건 검색
#select * from 테이블명 where 조건;
# 1학년 1반 학생을 조회
# 별명에 공백이 들어갈 경우에는 '' 혹은 ""붙여야함 
select num "1학년 1반 번호", name 이름 from student where grade = 1 and class = 1;

# 중복된 결과를 제거하는 distinct
# 학생이 등록된 학년과 반을 조회
select distinct grade, class from student;

# 조건에 들어가는 연산자
# AND, OR, NOT
# <, <=, >, >=, !=, <>, =
# 컬럼 between A and B : A이상 B이하 -> 컬럼 >= A and 컬럼 <= B
# 컬럼 not between A and B : A미만 또는 B초과 -> 컬럼 < A or 컬럼 > B
# 컬럼 in (값1, 값2, ..., 값N) : 컬럼의 값이 값1이거나 값2이거나 ... 값N인 행들을 조회
# 	-> 컬럼 = 값1 or 컬럼 = 값2 or ... or 컬럼 = 값N
# 컬럼 not in(값1, 값2, ..., 값N) : 값들과 일치하는 값이 없는 행들을 조회
#	-> 컬럼 != 값1 and 컬럼 != 값2 and ... and 컬럼 != 값N
# 컬럼 like "패턴" : 컬럼의 값이 패턴과 일치하면 조회
# - 패턴에 들어갈 수 있는 기호
#	- _ : 한글자 
# 	- % : 0글자이상
# 컬럼 is null : 컬럼값이 null이면 true
# 컬럼 is not null : 컬럼값이 null이 아니면 true

# 성이 홍씨인 학생을 조회
select * from student where name like "홍%";

# 이름이 3자인 학생을 조회
select * from student where name like "___";

# 이름에 길이 들어가는 학생을 조회
select * from student where name like "_%길%";

# 학생 이름이 등록된 학생
select * from student where name is not null; # name != null은 하면 무조건 false

# 1,2 학년 모두 조회 (두가지 결과는 같음)
select * from student where grade >= 1 and grade <=2;
select * from student where grade between 1 and 2;

# 번호가 1,3,5인 학생을 조회
select * from student where num = 1 or num = 3 or num = 5;
select * from student where num in(1,3,5);

# 정렬
# select * from 테이블 where 조건
# order by 속성명 [desc | asc], 속성명2 [desc | asc]
# desc : 내림차순, asc : 오름차순, asc인 경우 생략 가능 

# 학년은 내림차순, 반, 번호는 오름차순으로 정렬
select * from student order by grade desc, class, num;

# limit
# - 검색 결과 중 일부를 가져올 때 사용
# - 게시글에서 1페이지, 2페이지 눌렀을 때 게시글 목록이 다른데 이때 limit을 이용
# sclect * from 테이블 [where 조건] [order by 정렬] limit 시작 주소, 개수 ([]부분은 생략가능)
# - 시작 주소는 생략 가능. 시작 주소는 0번지부터

# 학생들을 조회하는 데 한번에 2명씩 조회가 가능할 때 1페이지에 조회되는 학생 목록
# limt (페이지 - 1) * 개수, 개수
select * from student limit 0, 2;

# 2페이지에 조회되는 학생 목록
select * from student limit 2, 2;

# 학생들이 1학년에 들어야할 과목을 조회하는 쿼리
select distinct title from subject where grade = 1;

# 학생들의 학년, 반, 번호를 낮은 학년부터 조회하는 쿼리
select * from student order by grade, class, num;

# 그룹화
# 레코드(행)들을 그룹으로 묶어서 처리할 때 사용 -> 1학년 1반 1학기 수학 평균
# 집계함수를 이용하여 조건을 걸 때 having을 사용
# select * from 
# where절 
# groub by 속성명1, 속성명2, ... 
# having 조건
# order by절 
# limit절

# 집계함수
# count(컬럼명) : 개수
# count(*)인 경우 모두가 null이 아니면 개수를 셈 -> 레코드 개수를 셈
# count(컬럼명)인 경우 컬럼이 null이면 개수를 안셈
# sum(컬럼명) : 합
# avg(컬럼명) : 평균
# min(컬럼명) : 최소값
# max(컬럼명) : 최대값
# 각 학년별 학생수를 조회
select grade, count(*) 학생수 from student group by grade;

# 각 과목별 총점을 조회
select sj_code 과목번호, sum(score) 총점 from score group by sj_code;

# 각 과목별 평균을 조회
select sj_code 과목번호, avg(score) 총점 from score group by sj_code;

# 각 과목별 최고점, 최하점을 조회
select sj_code 과목번호, max(score) 최고점, min(score) 최하점
from score group by sj_code;

# 1학년 1학기 때 들어야 할 과목수를 조회하는 쿼리
select grade 학년, semester 학기, count(title) 과목수  from subject
where grade = 1 and semester = 1;

# 각 학년, 학기별 들어야할 과목수를 조회하는 쿼리
select grade 학년, semester 학기, count(title) 과목수  from subject 
group by grade, semester;

# 평균이 각 학년 학기별 평균이 90이상인 과목들을 조회하는 쿼리
select sj_code 과목번호, avg(score) 평균
from score
# where avg(score) >= 90 # 실행시 에러발생. 집계합수는 where절에서 사용 X
group by sj_code
having 평균 >= 90; 	 # 평균대신 avg(score)도 가능

/*
select 컬럼1, 컬럼2, ...
from 테이블명
where 조건 
group by 컬럼명1, ...
having 조건
order by 컬럼명1, 정렬방법, ... 
limit 시작번지, 개수

1. from 테이블
2. where 절
3. group by 절
4. having 절
5. select 컬럼1, ...
6. order by
7. limit 
- 예외적인 상황
	- MySQL과 Oracle은 편의를 위해 having절에서 select의 별칭을 사용할 수 있도록 허용
*/

# JOIN
# 2개 이상의 테이블을 묶어서 하나의 결과를 만들 때 사용
# -> 설계할 때 정규화 과정을 통해 테이블을 나누기 때문에 필요한 데이터들이 여러 테이블에 나눠져 있음
# inner join : 두 테이블의 교집합
# outer join : 두 테이블의 합집합
# self join : 자기자신과 join

# inner join
# select * 속성
# from 테이블1
# join 테이블2
# on 테이블1.컬럼1 = 테이블2. 컬럼2;

# inner join2 
# 테이블1의 컬럼1과 테이블2의 컬럼2의 이름이 같은 경우
# select * 속성
# from 테이블1
# inner join 테이블2 	#inner는 생략 가능
# using(컬럼);

# 과목별 평균(학년, 학기)
select * #sj_code, avg(score) 평균
from score
join subject on score.sj_code = subject.code;
# group by sj_code;

# 학생별 성적 평균을 조회하는 쿼리
# 학년, 반, 번호, 이름, 평균이 조회 -> 성적이 등록 안된 1학년 1반 3번 학생은 조회가 안됨
select student.*, avg(score) 평균 from score
join student on score.st_code = student.code
group by st_code;

# inner join : 두 테이블에 연결된 데이터가 있을 때 조회가 됨.

# outer join : 두 테이블에 연결된 데이터가 없어도 조회가 필요할 때 사용
# 두 테이블을 연결한 후 연결할 데이터가 없는 쪽은 null처리
# left join / right join : 왼쪽/오른쪽 테이블 기준으로 연결

# left join : 테이블1을 기준으로 테이블1 옆에 테이블2를 이어 붙임
# select * from 테이블1
# left join 테이블2 on 테이블1.컬럼1 = 테이블2.컬럼2;

# 학생별 평균을 조회(성적이 등록되지 않은 학생도 포함)
SELECT 
    student.*, AVG(score) 평균
FROM
    score
        RIGHT JOIN
    student ON st_code = student.code
GROUP BY student.code;

# 학생의 성적을 조회(학년, 반, 번호, 이름, 학년, 학기, 과목, 성적) -> 등록된 모든학생이 조회
select student.grade 학년, student.class 반, student.num 번호, student.name 이름,
subject.grade 과목학년, semester 과목학기, title 과목명, score 성적
from score
right join subject on st_code = student.code
left join subject on sj_code = subject.code;


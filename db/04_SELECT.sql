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

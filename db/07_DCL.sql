# DCL : 데이터 조작어
# - 사용자 추가/삭제, 권한 부여/회수 

# 사용자 추가
# CREATE USER '아이디'@'호스트' IDENTIFIED BY '비번';
# 호스트 : 접근 권한 
# 	- localhost : 내부에서만 접근 가능 => 다른 PC에서 해당 계정으로 접근 못함
# 	- % : 외부에서도 접근 가능
CREATE USER 'abc123'@'%' IDENTIFIED BY 'abc123';

# 사용자 조회
SELECT USER, HOST FROM MYSQL.USER;

# 사용자 비번 변경
# SET PASSWORD FOR '아이디'@'호스트' = '새비번';
SET PASSWORD FOR 'abc123'@'%' = 'abc1234';

# 사용자 삭제
# DROP USER '아이디'@'호스트';
DROP USER 'abc123'@'%';

# 사용자 조회
SELECT USER, HOST FROM MYSQL.USER;

# 권한 부여를 위한 사용자 계정 추가
CREATE USER 'abc123'@'%' IDENTIFIED BY 'abc123';

# 권한 부여
# - 사용자에게 특정 DB의 접근 권한을 부여.
# - 테이블 추가/수정/삭제, 테이터 추가/수정/삭제/조회 등 
# - SELECT/INSERT/UPDATE/DELETE/CREATE/ALTER/DROP/REFERENCES
# - ALL PRIVILEGES(모든 권한)
# GRANT 권한 ON DB명.테이블명 TO 아이디 '아이디'@'호스트';
GRANT SELECT ON WORLD.* TO'abc123'@'%';
GRANT ALL PRIVILEGES ON WORLD.* TO'abc123'@'%';

# 권한 제거
# REVOKE 권한 ON DB명.테이블명 FROM '아이디'@'호스트';
# 쿼리 실행 정상. 왜인지 모르지만 에너 표시 뜸 (FROM에)
REVOKE ALL PRIVILEGES ON WORLD.* FROM 'abc123'@'%';

# 권한 확인
SHOW GRANTS FOR 'abc123'@'%';

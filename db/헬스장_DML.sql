# 센터를 운영할 트레이너 등록
# 팀장
# - 김철수 , 전공 : 보디빌딩, 경력 10년
INSERT INTO TRAINER(NAME, SPECIALTY, EXPERIENCE)
VALUES('김철수', '보디빌딩', 10);

# 신입 트레이너
# - 이영희, 전공 : 요가, 경력 2년
# - 홍길동, 전공 : 보디빌딩, 경력 3년
INSERT INTO TRAINER(NAME, SPECIALTY, EXPERIENCE)
VALUES('이영희', '요가', 2), ('홍길동', '보디빌딩', 3);

# 신입 트레이너의 팀장 배정 (#위에 등록할때 같이 해도 되지만 일단 신입을 뽑고 나중에 배정한다는 가정)
# - 이영희 : 김철수
# - 홍길동 : 김철수
UPDATE TRAINER 
	SET MANAGER_ID = '1'
	WHERE TRAINER_ID IN(2,3);
    
# 사물함 등록
# - 사물함은 2군데 : A구역, B구역
# 	- 사물함 별 번호, A구역은 1~5번, B구역은 1~15번
# - 사물함 이름: 구역명-번호
#	- ex) A구역-01  

INSERT INTO LOCKER(LOCATION)
VALUES('A구역-01'), ('A구역-02'), ('A구역-03'), ('A구역-04'), ('A구역-05'),
('B구역-01'),('B구역-02'),('B구역-03'),('B구역-04'),('B구역-05'),
('B구역-06'),('B구역-07'),('B구역-08'),('B구역-09'), ('B구역-10');

# 이순신 회원이 헬스장에 가입. 관리자는 A구역 1번 사물함을 배정
# 연락처 : 020-1111-1111, 가입일은 현재시간
# 기본값 CURRENT_TIMESTAMP
# - INSERT될 때 값을 넣지 않으면 기본값으로 현재 시간의 년,월,일,시,분초가 자동으로 추가
# - 단, DATETIME에서만 가능
INSERT INTO MEMBER(NAME, CONTACT, LOCKER_ID)
VALUES('이순신', '020-1111-1111', 1);

# 성춘향 회원이 헬스장에 가입. 관리자는 B구역 1번 사물함을 배정.
# 연락처 : 020-2222-2222, 가입일은 현재시간

INSERT INTO MEMBER(NAME, CONTACT, JOIN_DATE, LOCKER_ID)	#위랑 다른 방법으로 추가해보기
SELECT '성춘향', '020-2222-2222', NOW(), LOCKER_ID
	FROM LOCKER 
    # B구역 1번 사물함을 B구역-01로 변환하여 검색 후 추가 / # LPAD (1을, 두글자로, 두글자가 안되면 0을붙임)
	WHERE LOCATION = CONCAT('B구역', '-', LPAD(1, 2, '0')); 
SELECT * FROM MEMBER;

# 강좌 개설
# 강좌1
# - 강좌명 : 모닝 요가, 정원 20명, 수강료 100,000원, 담당 : 이영희(2)
# 강좌2
# - 강좌명 : 지옥의 크로스핏, 정원 15명, 수강료 150,000원 담당 : 홍길동(3)

INSERT INTO CLASS(NAME, CAPACITY, FEE, TRAINER_ID)
VALUES('모닝 요가', 20, 100000, 2), ('지옥의 크로스핏', 15, 150000, 3);

# 수강신청
# - 이순신(1) 회원이 모닝 요가(1)과 지옥의 크로스핏(2)을 수강 신청 후 결제 완료 함.
# - 성춘향(2) 회원이 모닝 요가(1)를 수강 신청 함
# - 결제 완료하면 '결제', 수강신청만 하면 '신청'으로 관리 

INSERT INTO ENROLLMENT(PAYMENT_STATE, MEMBER_ID, CLASS_ID)
VALUES
('결제', 1, 1), # 이순신 회원이 모닝요가를 수강 신청 후 결제
('결제', 1, 2), # 이순신 회원이 지옥의 크로스핏을 수강 신청 후 결제
('신청', 2, 1); # 성춘향 회원이 모닝요가를 수강 신청

# 이순신(1) 회원이 지옥의 크로스핏 강좌(2)를 출석체크 함(현재시간)

INSERT INTO ATTENDANCE(MEMBER_ID, CLASS_ID)
VALUES(1, 2);

# 새로운 강좌를 등록
# - 강좌명 : 크로스핏 초급반, 정원 : 30, 수강료 : 50,000
# - 담당 : 김철수(1)
INSERT INTO CLASS(NAME, CAPACITY, FEE, TRAINER_ID)
VALUES('크로스핏 초급반', 30, 50000, 1);


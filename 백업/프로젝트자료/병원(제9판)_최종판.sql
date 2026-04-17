DROP DATABASE IF EXISTS `hospital_scraps`;
-- CREATE DATABASE barodocq;
-- USE barodocq;

DROP TABLE IF EXISTS `hospital_scraps`;
DROP TABLE IF EXISTS `chat_messages`;
DROP TABLE IF EXISTS `chat_rooms`;
DROP TABLE IF EXISTS `pharmacy_hours`;
DROP TABLE IF EXISTS `hospital_events`;
DROP TABLE IF EXISTS `review_likes`;
DROP TABLE IF EXISTS `hospital_hours`;
DROP TABLE IF EXISTS `notifications`;
DROP TABLE IF EXISTS `review_comments`;
DROP TABLE IF EXISTS `review_files`;
DROP TABLE IF EXISTS `hospital_hours_updated_at`;
DROP TABLE IF EXISTS `hospital_departments`;
DROP TABLE IF EXISTS `pharmacy_info`;
DROP TABLE IF EXISTS `hospital_reviews`;
DROP TABLE IF EXISTS `user_social_accounts`;
DROP TABLE IF EXISTS `notification_category_detail`;
DROP TABLE IF EXISTS `notification_category`;
DROP TABLE IF EXISTS `reservations`;
DROP TABLE IF EXISTS `departments`;
DROP TABLE IF EXISTS `hospital_qna_answers`;
DROP TABLE IF EXISTS `hospital_qna`;
DROP TABLE IF EXISTS `hospital_admins`;
DROP TABLE IF EXISTS `hospital_info`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `hospital_admins` (
	`admin_num`	int	primary key AUTO_INCREMENT    NOT NULL,
	`admin_id`	varchar(50)	UNIQUE NOT NULL,
	`admin_pw`	varchar(256)	NOT NULL,
	`admin_name`	varchar(100)	NOT NULL,
	`admin_addr`	varchar(255)	NOT NULL,
	`admin_email`	varchar(100)	UNIQUE NOT NULL,
	`admin_phone`	varchar(20)	NOT NULL,
	`business_num`	varchar(13)	UNIQUE NOT NULL,
	`admin_terms_agreed`	BOOLEAN	NOT NULL,
	`admin_location_agreed`	BOOLEAN	NOT NULL,
	`admin_alert`	BOOLEAN	NOT NULL,
	`admin_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`admin_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ho_num`	int	NOT NULL UNIQUE,
    `role`	ENUM('ADMIN', 'SUPERADMIN')	NOT NULL
);

CREATE TABLE `reservations` (
	`re_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`re_date`	DATE	NOT NULL,
	`re_time`	TIME	NOT NULL,
	`re_status`	ENUM('예약대기', '예약확정', '예약거절', '진료완료', '예약취소')	NOT NULL,
	`re_visit_type`	ENUM('초진', '재진')	NOT NULL,
	`re_memo`	TEXT	NULL,
	`re_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`re_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`user_num`	int	NOT NULL,
	`ho_num`	int	NOT NULL,
	`dept_num`	int	NOT NULL
);

CREATE TABLE `chat_messages` (
	`cm_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`cm_sender_type`	ENUM('USER', 'ADMIN', 'SUPERADMIN')	NOT NULL,
	`cm_content`	TEXT	NOT NULL,
	`cm_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`cr_num`	INT	NOT NULL
);

CREATE TABLE `pharmacy_hours` (
	`phh_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`phh_day_of_week`	ENUM('월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일')	NOT NULL,
	`phh_open_time`	TIME	NOT NULL,
	`phh_close_time`	TIME	NOT NULL,
	`phh_open_yn`	BOOLEAN	NOT NULL,
	`phh_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`phh_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ph_num`	INT	NOT NULL
);

CREATE TABLE `hospital_events` (
	`ev_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`ev_title`	varchar(50)	NOT NULL,
	`ev_content`	TEXT	NOT NULL,
	`ev_view_count`	INT	NOT NULL DEFAULT 0,
	`ev_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`ev_start_date`	DATETIME	NULL,
	`ev_end_date`	DATETIME	NULL,
	`ev_hidden_yn`	BOOLEAN	NOT NULL,
	`ev_hidden_at`	DATETIME	NULL,
	`ev_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ho_num`	INT	NOT NULL,
	`admin_num`	INT	NOT NULL
);

CREATE TABLE `review_likes` (
	`rl_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`rl_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `rl_like`	BOOLEAN NOT NULL DEFAULT 0,
	`rv_num`	INT NOT NULL,
	`user_num`	INT	NOT NULL,
	UNIQUE KEY uq_rl_rv_user (rv_num, user_num)
);

CREATE TABLE `hospital_qna` (
	`qn_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`qn_title`	varchar(50)	NOT NULL,
	`qn_content`	TEXT	NOT NULL,
	`qn_is_private`	BOOLEAN	NOT NULL,
	`qn_status`	ENUM('답변기다리는중', '답변완료')	NOT NULL,
	`qn_view_count`	INT	NOT NULL DEFAULT 0,
	`qn_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`qn_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`qn_deleted_yn`	BOOLEAN	NOT NULL,
	`user_num`	INT	NOT NULL,
	`ho_num`	INT	NOT NULL
);

CREATE TABLE `hospital_hours` (
	`hh_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`hh_day_of_week`	ENUM('월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일')	NOT NULL,
	`hh_open_time`	TIME,
	`hh_close_time`	TIME,
	`hh_lunch_start`	TIME,
	`hh_lunch_end`	TIME,
	`hh_open_yn`	BOOLEAN	NOT NULL,
	`hh_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`hh_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ho_num`	int	NOT NULL
);

CREATE TABLE `notification_category_detail` (
	`ntd_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`ntd_template_name`	varchar(50)	NOT NULL,
	`ntd_content_template`	varchar(500)	NOT NULL,
	`ntc_category_num`	enum('1', '2', '3', '4')	NOT NULL
);

CREATE TABLE `notifications` (
	`nt_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`nt_is_read`	BOOLEAN	NOT NULL DEFAULT false,
	`nt_created_at`	DATETIME	NOT NULL,
	`user_num`	INT	NOT NULL,
	`nt_final_content`	varchar(500)	NOT NULL,
	`nt_url`	varchar(255)	NOT NULL,
	`ntd_num`	int	NOT NULL
);

CREATE TABLE `notification_category` (
	`ntc_category_num`	enum('1', '2', '3', '4') primary key	NOT NULL,
	`ntc_category_name`	varchar(50)	NOT NULL
);

CREATE TABLE `review_comments` (
	`rc_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`rc_ori_num`	INT	NOT NULL DEFAULT 0,
	`rc_content`	TEXT	NOT NULL,
	`rc_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`rc_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`rc_deleted_yn`	BOOLEAN	NOT NULL,
	`rv_num`	INT	NOT NULL,
	`user_num`	INT	NOT NULL
);

CREATE TABLE `review_files` (
	`rf_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`rf_ori_name`	varchar(255)	NOT NULL,
	`rf_name`	varchar(255)	NOT NULL,
	`rf_path`	varchar(500)	NOT NULL,
	`rf_order`	INT	NOT NULL,
	`rf_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`rv_num`	INT	NOT NULL
);

CREATE TABLE `hospital_hours_updated_at` (
	`hhu_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`hhu_day_of_week`	ENUM('월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일')	NOT NULL,
	`hhu_open_time`	TIME	NOT NULL,
	`hhu_close_time`	TIME	NOT NULL,
	`hhu_lunch_start`	TIME	NOT NULL,
	`hhu_lunch_end`	TIME	NOT NULL,
	`ho_num`	int	NOT NULL
);

CREATE TABLE `hospital_departments` (
	`jd_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`hd_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`ho_num`	int NOT NULL,
	`dept_num`	int NOT NULL,
	UNIQUE KEY uq_hd_ho_dept (ho_num, dept_num)
);

CREATE TABLE `user_social_accounts` (
	`usa_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`user_num`	int	NOT NULL,
	`usa_provider`	ENUM('네이버', '카카오', '구글', '바로닥큐') NOT NULL,
	`usa_provider_user_id`	varchar(50)	NOT NULL,
	`usa_email`	varchar(100)	NOT NULL,
	`usa_name`	varchar(50)	NOT NULL,
	`usa_connected_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`usa_last_login_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`usa_unlinked_yn`	BOOLEAN	NOT NULL,
	UNIQUE KEY uq_usa_provider_usaprouserid (usa_provider, usa_provider_user_id)
);

CREATE TABLE `hospital_reviews` (
	`rv_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`rv_rating`	TINYINT UNSIGNED CHECK(rv_rating BETWEEN 1 AND 5) NOT NULL,
	`rv_title`	varchar(50)	NOT NULL,
	`rv_content`	TEXT	NOT NULL,
	`rv_comment_count`	INT	NOT NULL DEFAULT 0,
	`rv_view_count`	INT	NOT NULL DEFAULT 0,
    `rv_likes_count`	INT	NOT NULL DEFAULT 0,
	`rv_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`rv_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`rv_deleted_yn`	BOOLEAN	NOT NULL,
	`user_num`	INT	NOT NULL,
	`re_num`	INT	UNIQUE NOT NULL,
	`ho_num`	int	NOT NULL
);

CREATE TABLE `pharmacy_info` (
	`ph_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`ph_name`	varchar(50)	NOT NULL,
	`ph_phone`	varchar(20)	NOT NULL,
	`ph_addr`	TEXT	NOT NULL,
	`ph_lat`	TEXT,
	`ph_lng`	TEXT,
	`ph_direction`	text	NOT NULL,
	`ph_photo`	varchar(500),
	`ph_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`ph_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ph_night_yn`	BOOLEAN	NOT NULL,
	`ph_holiday_yn`	BOOLEAN	NOT NULL
);

CREATE TABLE `hospital_info` (
	`ho_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`ho_name`	varchar(50)	NOT NULL,
	`ho_email`	varchar(255),
	`ho_phone`	varchar(20)	NOT NULL,
	`ho_addr`	varchar(255)	NOT NULL,
	`ho_lat`	DECIMAL(15,8)	NULL,
	`ho_lng`	DECIMAL(15,8)	NULL,
	`ho_doc_count`	int	NULL DEFAULT 0,
	`ho_parking`	boolean	NULL,
	`ho_photo`	varchar(500),
	`ho_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`ho_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`ho_night_yn`	BOOLEAN	NOT NULL,
	`ho_holiday_yn`	BOOLEAN	NOT NULL
);

CREATE TABLE `hospital_scraps` (
	`hs_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`hs_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`ho_num`	INT	NOT NULL,
	`user_num`	INT NOT NULL,
	UNIQUE KEY uq_hs_user_ho (user_num, ho_num)
);

CREATE TABLE `chat_rooms` (
	`cr_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`cr_status`	ENUM('ACTIVE', 'CLOSED')	NOT NULL,
	`cr_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`cr_closed_at`	DATETIME	NULL,
	`cr_closed_by`	ENUM('사용자', '병원', '시스템가능')	NOT NULL,
	`cr_last_msg_at`	DATETIME	NULL,
	`cr_last_msg_preview`	varchar(255)	NULL,
	`ho_num`	int	NOT NULL,
	`user_num`	int	NOT NULL
);

CREATE TABLE `hospital_qna_answers` (
	`qa_num`	INT	primary key AUTO_INCREMENT NOT NULL,
	`qa_content`	TEXT	NOT NULL,
	`qa_view_count`	INT	NOT NULL DEFAULT 0,
	`qa_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`qa_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`qa_deleted_yn`	BOOLEAN	NOT NULL DEFAULT 0,
	`qn_num`	INT	NOT NULL,
	`admin_num`	INT	NOT NULL
);

CREATE TABLE `users` (
	`user_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`user_id`	varchar(50)	UNIQUE NOT NULL,
	`user_pw`	varchar(256)	NOT NULL,
	`user_name`	varchar(50)	NOT NULL,
	`user_addr`	varchar(255)	NOT NULL,
	`user_email`	varchar(100) UNIQUE	NOT NULL,
	`user_phone`	varchar(20)	NOT NULL,
	`user_birth`	DATE	NOT NULL,
	`user_created_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`user_updated_at`	DATETIME	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`user_gender`	ENUM('MALE', 'FEMALE', 'OTHER')	NOT NULL,
	`user_alert`	BOOLEAN	NOT NULL DEFAULT TRUE,
	`terms_agreed`	BOOLEAN	NOT NULL,
	`location_agreed`	BOOLEAN	NOT NULL,
    `role`	ENUM('USER', 'ADMIN', 'SUPERADMIN')	NOT NULL
);

CREATE TABLE `departments` (
	`dept_num`	int	primary key AUTO_INCREMENT NOT NULL,
	`dept_name`	varchar(100) UNIQUE NOT NULL,
	`ho_num`	int	NOT NULL
);


ALTER TABLE hospital_admins
  ADD CONSTRAINT FK_hospital_info_TO_hospital_admins
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE reservations
  ADD CONSTRAINT FK_users_TO_reservations
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE reservations
  ADD CONSTRAINT FK_hospital_info_TO_reservations
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE reservations
  ADD CONSTRAINT FK_departments_TO_reservations
  FOREIGN KEY (dept_num)
  REFERENCES departments(dept_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE chat_messages
  ADD CONSTRAINT FK_chat_rooms_TO_chat_messages
  FOREIGN KEY (cr_num)
  REFERENCES chat_rooms(cr_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE pharmacy_hours
  ADD CONSTRAINT FK_pharmacy_info_TO_pharmacy_hours
  FOREIGN KEY (ph_num)
  REFERENCES pharmacy_info(ph_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_events
  ADD CONSTRAINT FK_hospital_info_TO_hospital_events
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_events
  ADD CONSTRAINT FK_hospital_admins_TO_hospital_events
  FOREIGN KEY (admin_num)
  REFERENCES hospital_admins(admin_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE review_likes
  ADD CONSTRAINT FK_hospital_reviews_TO_review_likes
  FOREIGN KEY (rv_num)
  REFERENCES hospital_reviews(rv_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE review_likes
  ADD CONSTRAINT FK_users_TO_review_likes
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_qna
  ADD CONSTRAINT FK_users_TO_hospital_qna
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_qna
  ADD CONSTRAINT FK_hospital_info_TO_hospital_qna
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_hours
  ADD CONSTRAINT FK_hospital_info_TO_hospital_hours
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE notification_category_detail
  ADD CONSTRAINT FK_notification_category_TO_notification_category_detail
  FOREIGN KEY (ntc_category_num)
  REFERENCES notification_category(ntc_category_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE notifications
  ADD CONSTRAINT FK_users_TO_notifications
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE notifications
  ADD CONSTRAINT FK_notification_category_detail_TO_notifications
  FOREIGN KEY (ntd_num)
  REFERENCES notification_category_detail(ntd_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE review_comments
  ADD CONSTRAINT FK_hospital_reviews_TO_review_comments
  FOREIGN KEY (rv_num)
  REFERENCES hospital_reviews(rv_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE review_comments
  ADD CONSTRAINT FK_users_TO_review_comments
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE review_files
  ADD CONSTRAINT FK_hospital_reviews_TO_review_files
  FOREIGN KEY (rv_num)
  REFERENCES hospital_reviews(rv_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_hours_updated_at
  ADD CONSTRAINT FK_hospital_info_TO_hospital_hours_updated_at
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_departments
  ADD CONSTRAINT FK_hospital_info_TO_hospital_departments
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_departments
  ADD CONSTRAINT FK_departments_TO_hospital_departments
  FOREIGN KEY (dept_num)
  REFERENCES departments(dept_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE user_social_accounts
  ADD CONSTRAINT FK_users_TO_user_social_accounts
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_reviews
  ADD CONSTRAINT FK_users_TO_hospital_reviews
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_reviews
  ADD CONSTRAINT FK_reservations_TO_hospital_reviews
  FOREIGN KEY (re_num)
  REFERENCES reservations(re_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_reviews
  ADD CONSTRAINT FK_hospital_info_TO_hospital_reviews
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_scraps
  ADD CONSTRAINT FK_hospital_info_TO_hospital_scraps
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_scraps
  ADD CONSTRAINT FK_users_TO_hospital_scraps
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE chat_rooms
  ADD CONSTRAINT FK_hospital_info_TO_chat_rooms
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE chat_rooms
  ADD CONSTRAINT FK_users_TO_chat_rooms
  FOREIGN KEY (user_num)
  REFERENCES users(user_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_qna_answers
  ADD CONSTRAINT FK_hospital_qna_TO_hospital_qna_answers
  FOREIGN KEY (qn_num)
  REFERENCES hospital_qna(qn_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE hospital_qna_answers
  ADD CONSTRAINT FK_hospital_admins_TO_hospital_qna_answers
  FOREIGN KEY (admin_num)
  REFERENCES hospital_admins(admin_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE departments
  ADD CONSTRAINT FK_hospital_info_TO_departments
  FOREIGN KEY (ho_num)
  REFERENCES hospital_info(ho_num)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

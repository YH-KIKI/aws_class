
# 대학생 DDL
# DB 추가
DROP DATABASE IF EXISTS UNIVERSITY;

CREATE DATABASE UNIVERSITY;
USE UNIVERSITY;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
	`st_num`	char(10)	PRIMARY KEY,
	`st_name`	varchar(20)	NOT NULL,
	`st_contact`	varchar(13)	NOT NULL,
	`st_resident`	char(14)	NOT NULL UNIQUE,
	`st_year`	year	NOT NULL,
	`st_pr_num`	char(11)	NULL
);

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
	`mj_code`	char(3)	PRIMARY KEY,
	`mj_office`	varchar(100)	NULL,
	`mj_name`	varchar(20)	NOT NULL,
	`mj_pr_num`	char(11)	NULL
);

DROP TABLE IF EXISTS `professor`;

CREATE TABLE `professor` (
	`pr_num`	char(11)	PRIMARY KEY,
	`pr_name`	varchar(20)	NOT NULL,
	`pr_contact`	varchar(13)	NOT NULL,
	`pr_resident`	char(14)	NOT NULL UNIQUE,
	`pr_year`	year	NOT NULL ,
	`pr_mj_code`	char(3)	NOT NULL
);

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
	`sj_code`	char(6)	PRIMARY KEY,
	`sj_name`	varchar(20)	NOT NULL,
	`sj_time`	int	NOT NULL DEFAULT 0,
	`sj_point`	int	NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS `student_major`;

CREATE TABLE `student_major` (
	`sm_num`	int	PRIMARY KEY AUTO_INCREMENT,
	`sm_st_num`	char(10)	NOT NULL,
	`sm_mj_code`	char(3)	NOT NULL
);

DROP TABLE IF EXISTS `lecture`;

CREATE TABLE `lecture` (
	`lt_num`	int	PRIMARY KEY AUTO_INCREMENT,
	`lt_pr_num`	char(11)	NOT NULL,
	`lt_sj_code`	char(6)	NOT NULL,
	`lt_year`	int	NOT NULL,
	`lt_semester`	varchar(2)	NOT NULL,
	`lt_schedule`	varchar(100)	NOT NULL,
	`lt_room`	varchar(100)	NULL,
	`lt_syllabus`	text	NOT NULL,
	`lt_class`	int	NOT NULL DEFAULT 1,
	`lt_max`	int	NOT NULL,
	`lt_current`	int	NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
	`co_num`	int	PRIMARY KEY AUTO_INCREMENT,
	`co_st_num`	char(10)	NOT NULL,
	`co_lt_num`	int	NOT NULL,
	`co_score`	varchar(4)	NULL,
	`co_mid`	int	NOT NULL DEFAULT 0,
	`co_final`	int	NOT NULL DEFAULT 0,
	`co_hw`	int	NOT NULL DEFAULT 0,
	`co_att`	int	NOT NULL DEFAULT 0
);

ALTER TABLE `student` ADD CONSTRAINT `FK_professor_TO_student_1` FOREIGN KEY (
	`st_pr_num`
)
REFERENCES `professor` (
	`pr_num`
);

ALTER TABLE `major` ADD CONSTRAINT `FK_professor_TO_major_1` FOREIGN KEY (
	`mj_pr_num`
)
REFERENCES `professor` (
	`pr_num`
);

ALTER TABLE `professor` ADD CONSTRAINT `FK_major_TO_professor_1` FOREIGN KEY (
	`pr_mj_code`
)
REFERENCES `major` (
	`mj_code`
);

ALTER TABLE `student_major` ADD CONSTRAINT `FK_student_TO_student_major_1` FOREIGN KEY (
	`sm_st_num`
)
REFERENCES `student` (
	`st_num`
);

ALTER TABLE `student_major` ADD CONSTRAINT `FK_major_TO_student_major_1` FOREIGN KEY (
	`sm_mj_code`
)
REFERENCES `major` (
	`mj_code`
);

ALTER TABLE `lecture` ADD CONSTRAINT `FK_professor_TO_lecture_1` FOREIGN KEY (
	`lt_pr_num`
)
REFERENCES `professor` (
	`pr_num`
);

ALTER TABLE `lecture` ADD CONSTRAINT `FK_subject_TO_lecture_1` FOREIGN KEY (
	`lt_sj_code`
)
REFERENCES `subject` (
	`sj_code`
);

ALTER TABLE `course` ADD CONSTRAINT `FK_student_TO_course_1` FOREIGN KEY (
	`co_st_num`
)
REFERENCES `student` (
	`st_num`
);

ALTER TABLE `course` ADD CONSTRAINT `FK_lecture_TO_course_1` FOREIGN KEY (
	`co_lt_num`
)
REFERENCES `lecture` (
	`lt_num`
);


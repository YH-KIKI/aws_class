# https://www.erdcloud.com/d/PsDTmWwCgyxQgbSxe

# DROP DATABASE IF EXISTS community;

# CREATE DATABASE community;

# USE community;

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
	`bo_num`	int  primary key auto_increment	NOT NULL,
	`bo_name`	varchar(20) unique not	NULL
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
	`po_num`	int  primary key auto_increment	NOT NULL,
	`po_title`	varchar(255) not	NULL,
	`po_content`	longtext not	NULL,
	`po_date`	datetime default current_timestamp not	NULL,
	`po_view`	int default 0 not	NULL,
	`po_up`	int default 0 not	NULL,
	`po_down`	int default 0 not	NULL,
	`po_me_id`	varchar(15)	NOT NULL,
	`po_bo_num`	int	NOT NULL,
	`po_del`	char(1) default 'N' not	NULL
);

DROP TABLE IF EXISTS `like`;

CREATE TABLE `like` (
	`li_num`	int primary key auto_increment	NOT NULL,
	`li_state`	int not	NULL,
	`li_me_id`	varchar(15)	NOT NULL,
	`li_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
	`fi_num`	int  primary key auto_increment	NOT NULL,
	`fi_ori_name`	varchar(255) not	NULL,
	`fi_name`	varchar(255) not	NULL,
	`fi_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
	`co_num`	int primary key	NOT NULL,
	`co_content`	varchar(200) not	NULL,
	`co_date`	datetime default current_timestamp not	NULL,
	`co_ori_num`	int not	NULL,
	`co_del`	char(1) default 'N' not	NULL,
	`co_me_id`	varchar(15)	NOT NULL,
	`co_po_num`	int	NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`me_id`	varchar(15) primary key	NOT NULL,
	`me_pw`	varchar(255) not	NULL,
	`me_email`	varchar(255) not	NULL,
	`me_role`	varchar(5) default "USER" not	NULL
);

ALTER TABLE `post` ADD CONSTRAINT `FK_MEMBER_TO_POST_1` FOREIGN KEY (
	`po_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_BOARD_TO_POST_1` FOREIGN KEY (
	`po_bo_num`
)
REFERENCES `board` (
	`bo_num`
);

ALTER TABLE `like` ADD CONSTRAINT `FK_MEMBER_TO_LIKE_1` FOREIGN KEY (
	`li_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `like` ADD CONSTRAINT `FK_POST_TO_LIKE_1` FOREIGN KEY (
	`li_po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `file` ADD CONSTRAINT `FK_POST_TO_FILE_1` FOREIGN KEY (
	`fi_po_num`
)
REFERENCES `post` (
	`po_num`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_MEMBER_TO_COMMENT_1` FOREIGN KEY (
	`co_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_POST_TO_COMMENT_1` FOREIGN KEY (
	`co_po_num`
)
REFERENCES `post` (
	`po_num`
);


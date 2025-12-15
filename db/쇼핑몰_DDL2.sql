
DROP DATABASE IF EXISTS SHOPPINGMALL2;

CREATE DATABASE SHOPPINGMALL2;

USE SHOPPINGMALL2;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id`	varchar(13)	PRIMARY KEY,
	`pw`	varchar(20)	NULL,
	`email`	varchar(50)	NULL,
	`phone`	varchar(13)	NULL
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`num`	int	PRIMARY KEY auto_increment,
	`code`	char(3)	NOT NULL UNIQUE,
	`title`	varchar(10)	NOT NULL UNIQUE
);

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
	`code`	char(6)	PRIMARY KEY,
	`title`	varchar(100)	NOT NULL,
	`content`	longtext	NOT NULL,
	`amount`	int	NOT NULL DEFAULT 0,
	`price`	int	NOT NULL,
	`thumb`	varchar(255)	NULL,
	`num`	int	NOT NULL
);

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
	`num`	int	PRIMARY KEY auto_increment,
	`amount`	int	NOT NULL,
	`id`	varchar(13)	NOT NULL,
	`code`	char(6)	NOT NULL
);

DROP TABLE IF EXISTS `buy`;

CREATE TABLE `buy` (
	`num`	int	PRIMARY KEY auto_increment,
	`address`	varchar(100)	NOT NULL,
	`amount`	int	NOT NULL,
	`code`	char(6)	NOT NULL,
    `price` int NOT NULL,
	`id`	varchar(13)	NOT NULL
);

ALTER TABLE `product` ADD CONSTRAINT `FK_category_TO_product_1` FOREIGN KEY (
	`num`
)
REFERENCES `category` (
	`num`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_user_TO_cart_1` FOREIGN KEY (
	`id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_product_TO_cart_1` FOREIGN KEY (
	`code`
)
REFERENCES `product` (
	`code`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_product_TO_buy_1` FOREIGN KEY (
	`code`
)
REFERENCES `product` (
	`code`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_user_TO_buy_1` FOREIGN KEY (
	`id`
)
REFERENCES `user` (
	`id`
);
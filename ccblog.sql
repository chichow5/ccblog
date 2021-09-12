CREATE DATABASE ccblog;
USE ccblog;

--- --------------------------
--- Table structure for `user`
--- --------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` 		int(11) 		NOT NULL AUTO_INCREMENT,
    `username` 	varchar(200) 	DEFAULT NULL,
    `password` 	varchar(200) 	DEFAULT NULL,
    `email` 	varchar(200) 	DEFAULT NULL,
    `created` 	date 			DEFAULT NULL,
    `valid` 	tinyint(1) 		NOT NULL DEFAULT '1',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--- --------------------------
--- Table structure for `authority`
--- --------------------------

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
    `id` 		int(11) 		NOT NULL AUTO_INCREMENT,
    `authority` varchar(200) 	DEFAULT NULL COMMENT '权限',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `authority` VALUES ('1', 'ROLE_admin');
INSERT INTO `authority` VALUES ('2', 'ROLE_common');

--- --------------------------
--- Table structure for `user_authority`
--- --------------------------

DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
    `id` 				int(11) NOT NULL AUTO_INCREMENT,
    `user_id` 			int(11) NOT NULL COMMENT '关联用户id',
    `authority_type` 	int(11) NOT NULL COMMENT '关联用户权限类型',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_authority` VALUES ('1','1','1');
INSERT INTO `user_authority` VALUES ('2','2','2');

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
    `username` 	varchar(64) NOT NULL,
    `series` 	varchar(64) NOT NULL,
    `token` 	varchar(64) NOT NULL,
    `last_used` timestamp 	NOT NULL
);

--- --------------------------
--- Table structure for `article`
--- --------------------------

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
    `id` 			int(11) 		NOT NULL AUTO_INCREMENT,
    `title` 		varchar(50) 	NOT NULL COMMENT '标题',
    `content` 		varchar(300) 	NOT NULL COMMENT '内容（开头截取）',
    `created` 		date 			NOT NULL COMMENT '发表时间',
    `modified` 		date 			NOT NULL COMMENT '修改时间',
    `ctg_type`		int(11) 		NOT NULL DEFAULT '0' COMMENT '分类',
    `tags` 			varchar(200) 	NOT NULL COMMENT '标签',
    `allow_comment` 	tinyint(1) 		NOT NULL COMMENT '是否允许评论',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
	`type` 		int(11)			NOT NULL AUTO_INCREMENT,
	`string`	varchar(200) 	NOT NULL,
	PRIMARY KEY(`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `categories` VALUES ('0', '默认分类');


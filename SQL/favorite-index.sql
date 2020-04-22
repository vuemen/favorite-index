CREATE DATABASE IF NOT EXISTS `favorite_index` default character set = 'utf8';

USE `favorite_index`;

DROP TABLE IF EXISTS `fi_user_info`;
CREATE TABLE `fi_user_info` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `EMAIL` char(50) NOT NULL COMMENT '邮箱地址',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `HEAD_IMG` varchar(200) NOT NULL COMMENT '头像连接',
  `CREATE_TIME` char(19) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` char(19) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

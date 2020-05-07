CREATE DATABASE IF NOT EXISTS `favorite_index` default character set = 'utf8';

USE `favorite_index`;

DROP TABLE IF EXISTS `fi_user_info`;
CREATE TABLE `fi_user_info` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `EMAIL` char(50) NOT NULL COMMENT '邮箱地址',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `HEAD_IMG` varchar(200) NOT NULL COMMENT '头像链接',
  `HIS_SWITCH` char(1) NOT NULL COMMENT '搜索历史开关',
  `BGI_FLOW_SYSTEM` char(1) NOT NULL COMMENT '背景图是否跟随系统',
  `BGI_LINK` varchar(200) DEFAULT NULL COMMENT '背景图链接,背景图是否跟随系统为否时使用',
  `CREATE_TIME` char(19) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` char(19) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fi_search_history`;
CREATE TABLE `fi_search_history` (
  `HIS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '历史ID',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `HISTORY` varchar(200) NOT NULL COMMENT '历史',
  `CREATE_TIME` char(19) NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` char(19) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`HIS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
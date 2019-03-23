/*
Navicat MySQL Data Transfer
Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : mansys
Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001
Date: 2018-01-06 18:38:34
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `contact`
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id`         INT(11)      NOT NULL AUTO_INCREMENT,
  `owner_id`   INT(11)      NOT NULL,
  `name`       VARCHAR(255) NOT NULL,
  `gender`     INT(11)      NOT NULL,
  `phone_num`  VARCHAR(255) NOT NULL,
  `created_at` BIGINT(20)   NOT NULL,
  `updated_at` BIGINT(20)   NOT NULL,
  `status`     INT(11)      NOT NULL,
  `remark`     VARCHAR(255)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ID` (`owner_id`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES ('1', '1', 'qinkai', '2', '11', '12345', '211', '1', NULL);
INSERT INTO `contact` VALUES ('2', '3', 'qinkai', '2', '11', '123454', '4332', '0', NULL);
INSERT INTO `contact` VALUES ('3', '1', '1', '1', '1', '1515235076551', '1515235076551', '-1', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`        INT(11)      NOT NULL AUTO_INCREMENT,
  `username`  VARCHAR(255) NOT NULL,
  `password`  VARCHAR(255) NOT NULL,
  `phone_num` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'qinkai', '111111', '1');
INSERT INTO `user` VALUES ('2', 'huhua', 'qazwsx', '20');
INSERT INTO `user` VALUES ('3', 'qq', '11', '0');
INSERT INTO `user` VALUES ('4', 'lala', '11', '1');
INSERT INTO `user` VALUES ('5', 'aa', '11', '3144');
INSERT INTO `user` VALUES ('6', 'asas', '11', '343434');
INSERT INTO `user` VALUES ('11', 'ha', '11', '423');
INSERT INTO `user` VALUES ('12', 'ha', '11', '23443');
INSERT INTO `user` VALUES ('13', '111111', '1', '1');
INSERT INTO `user` VALUES ('14', '222222', '2', '2');
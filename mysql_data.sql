/*
Navicat MySQL Data Transfer

Source Server         : local - 3306
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-11-17 19:45:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for x_user
-- ----------------------------
DROP TABLE IF EXISTS `x_user`;
CREATE TABLE `x_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `add_time` int(11) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='审核人员';

-- ----------------------------
-- Records of x_user
-- ----------------------------
INSERT INTO `x_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'sssasd', '15613222321', '1532412311', '1');
INSERT INTO `x_user` VALUES ('3', '213123', '123123', '123qeqwe', '22312312', '1212121212', '1');
INSERT INTO `x_user` VALUES ('4', 'sss', '123123', 'qwe', '2123123123', '1523212341', '1');
INSERT INTO `x_user` VALUES ('5', '12121', '212', '121qweqwe', '12121212', '1542312341', '11');
INSERT INTO `x_user` VALUES ('6', '1555', '23123123', 'LetUEvOrFd_!!3946.txt', '22', '1425123412', '1');
INSERT INTO `x_user` VALUES ('7', '12', '213', 'VoTuEbctDs_!!3946.txt', '123', '1423341234', '1');

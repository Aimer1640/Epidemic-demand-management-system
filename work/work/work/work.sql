/*
Navicat MySQL Data Transfer

Source Server         : 10
Source Server Version : 50149
Source Host           : 127.0.0.1:3308
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2022-06-09 09:58:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` varchar(50) NOT NULL COMMENT '管理员id',
  `adminname` varchar(255) NOT NULL COMMENT '管理员名',
  `password` varchar(255) NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123', '123', '123');

-- ----------------------------
-- Table structure for need
-- ----------------------------
DROP TABLE IF EXISTS `need`;
CREATE TABLE `need` (
  `needId` varchar(50) NOT NULL COMMENT '需求ID',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `needThing` varchar(255) DEFAULT NULL COMMENT '需求物品',
  `grade` int(255) DEFAULT '0' COMMENT '很紧急为1\r\n较紧急为0',
  PRIMARY KEY (`needId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of need
-- ----------------------------
INSERT INTO `need` VALUES ('1', '101', '2040706', '青菜', '0');
INSERT INTO `need` VALUES ('2', '101', '2040706', '作业纸', '1');
INSERT INTO `need` VALUES ('3', '102', '2040707', '作业纸', '0');
INSERT INTO `need` VALUES ('4', '101', '2040706', '青菜', '1');
INSERT INTO `need` VALUES ('5', '101', '2040706', '作业纸', '1');
INSERT INTO `need` VALUES ('6', '103', '2040708', '青菜', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(50) NOT NULL COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('101', '101', '101');
INSERT INTO `user` VALUES ('102', '102', '102');
INSERT INTO `user` VALUES ('103', '103', '103');
INSERT INTO `user` VALUES ('105', '105', '105');

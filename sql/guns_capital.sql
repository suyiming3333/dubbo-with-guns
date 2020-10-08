/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : guns_capital

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-10-08 22:25:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cap_capital_account
-- ----------------------------
DROP TABLE IF EXISTS `cap_capital_account`;
CREATE TABLE `cap_capital_account` (
  `CAPITAL_ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BALANCE_AMOUNT` decimal(10,0) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CAPITAL_ACCOUNT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cap_capital_account
-- ----------------------------
INSERT INTO `cap_capital_account` VALUES ('1', '10000', '1');
INSERT INTO `cap_capital_account` VALUES ('2', '470', '2');

-- ----------------------------
-- Table structure for cap_trade_order
-- ----------------------------
DROP TABLE IF EXISTS `cap_trade_order`;
CREATE TABLE `cap_trade_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SELF_USER_ID` bigint(11) DEFAULT NULL,
  `OPPOSITE_USER_ID` bigint(11) DEFAULT NULL,
  `MERCHANT_ORDER_NO` varchar(45) DEFAULT NULL,
  `AMOUNT` decimal(10,0) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cap_trade_order
-- ----------------------------
INSERT INTO `cap_trade_order` VALUES ('23', '2', null, '415sdf58ew12ds5fe1', '10', 'CONFIRM');

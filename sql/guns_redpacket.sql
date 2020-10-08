/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : guns_redpacket

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-10-08 22:26:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for red_red_packet_account
-- ----------------------------
DROP TABLE IF EXISTS `red_red_packet_account`;
CREATE TABLE `red_red_packet_account` (
  `RED_PACKET_ACCOUNT_ID` int(11) NOT NULL,
  `BALANCE_AMOUNT` decimal(10,0) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RED_PACKET_ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of red_red_packet_account
-- ----------------------------
INSERT INTO `red_red_packet_account` VALUES ('1', '950', '1');
INSERT INTO `red_red_packet_account` VALUES ('2', '40', '2');

-- ----------------------------
-- Table structure for red_trade_order
-- ----------------------------
DROP TABLE IF EXISTS `red_trade_order`;
CREATE TABLE `red_trade_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SELF_USER_ID` bigint(11) DEFAULT NULL,
  `OPPOSITE_USER_ID` bigint(11) DEFAULT NULL,
  `MERCHANT_ORDER_NO` varchar(45) DEFAULT NULL,
  `AMOUNT` decimal(10,0) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of red_trade_order
-- ----------------------------
INSERT INTO `red_trade_order` VALUES ('19', '2', null, '415sdf58ew12ds5fe1', '10', 'CONFIRM');

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : guns_user

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-08-29 22:15:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mooc_user_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_user_t`;
CREATE TABLE `mooc_user_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `user_pwd` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `user_sex` int(11) DEFAULT NULL COMMENT '用户性别 0-男，1-女',
  `birthday` varchar(50) DEFAULT NULL COMMENT '出生日期',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `address` varchar(50) DEFAULT NULL COMMENT '用户住址',
  `head_url` varchar(50) DEFAULT NULL COMMENT '头像URL',
  `biography` varchar(200) DEFAULT NULL COMMENT '个人介绍',
  `life_state` int(11) DEFAULT NULL COMMENT '生活状态 0-单身，1-热恋中，2-已婚，3-为人父母',
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of mooc_user_t
-- ----------------------------
INSERT INTO `mooc_user_t` VALUES ('2', 'admin', '0192023a7bbd73250516f069df18b500', '隔壁泰山', '0', '2018-07-31', 'admin@mooc.com', '13888888888', '北京市海淀区朝阳北路中南海国宾馆', 'films/img/head-img.jpg', '没有合适的伞，我宁可淋着雨', '0', '2020-08-19 22:50:53', '2020-08-19 22:50:53');
INSERT INTO `mooc_user_t` VALUES ('3', 'jiangzh', '5e2de6bd1c9b50f6e27d4e55da43b917', '阿里郎', '0', '2018-08-20', 'jiangzh@mooc.com', '13866666666', '北京市朝阳区大望路万达广场', 'films/img/head-img.jpg', '我喜欢隔壁泰山', '1', '2020-08-19 22:50:53', '2020-08-19 22:50:53');
INSERT INTO `mooc_user_t` VALUES ('4', 'suyiming', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, 'suyiming@163.com', '13265905397', '广州市天河区', null, null, null, '2020-08-29 20:43:04', '2020-08-29 20:43:04');

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : guns_order

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-10-08 22:26:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mooc_order_2017_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_order_2017_t`;
CREATE TABLE `mooc_order_2017_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` int(11) DEFAULT NULL COMMENT '下单人',
  `order_status` int(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

-- ----------------------------
-- Records of mooc_order_2017_t
-- ----------------------------
INSERT INTO `mooc_order_2017_t` VALUES ('329123812gnfn31', '1', '1', '2', '1,2,3,4', '第一排1座,第一排2座,第一排3座,第一排4座', '63.2', '126.4', '2017-05-03 12:13:42', '1', '0');

-- ----------------------------
-- Table structure for mooc_order_2018_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_order_2018_t`;
CREATE TABLE `mooc_order_2018_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` int(11) DEFAULT NULL COMMENT '下单人',
  `order_status` int(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

-- ----------------------------
-- Records of mooc_order_2018_t
-- ----------------------------
INSERT INTO `mooc_order_2018_t` VALUES ('124583135asdf81', '1', '1', '2', '1,2,3,4', '第一排1座,第一排2座,第一排3座,第一排4座', '63.2', '126.4', '2018-02-12 11:53:42', '1', '0');

-- ----------------------------
-- Table structure for mooc_order_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_order_t`;
CREATE TABLE `mooc_order_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` int(11) DEFAULT NULL COMMENT '下单人',
  `order_status` varchar(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭',
  `film_start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `file_end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cinema_name` varchar(255) DEFAULT NULL,
  `film_name` varchar(255) DEFAULT NULL,
  `filed_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

-- ----------------------------
-- Records of mooc_order_t
-- ----------------------------
INSERT INTO `mooc_order_t` VALUES ('415sdf58ew12ds5fe1', '1', '1', '2', '1,2,3,4', '第一排1座,第一排2座,第一排3座,第一排4座', '63.2', '126.4', '2020-08-31 23:06:28', '2', 'CONFIRMED', '2020-10-08 14:53:09', '2020-10-08 14:53:09', '大地影院(顺义店)', '我不是药神', '9月2号 9点50分');
INSERT INTO `mooc_order_t` VALUES ('96d5b79bf58e4a35a0b7c107dd7b3d81', '1', '2', '3', '7,8,9', '七八九号座', '60', '180', '2020-09-03 22:03:21', '2', '0', '2020-09-03 22:03:21', '2020-09-03 22:03:21', null, null, '爱情公寓');
INSERT INTO `mooc_order_t` VALUES ('6dc72c5abd6144978a7f3832100634dd', '1', '2', '3', '1,2,3,4', '七八九号座', '60', '240', '2020-09-16 23:08:08', '2', '0', '2020-09-16 23:08:08', '2020-09-16 23:08:08', null, '爱情公寓', null);

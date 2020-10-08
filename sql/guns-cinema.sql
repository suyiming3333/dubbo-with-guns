/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : guns-cinema

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-10-08 22:26:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mooc_area_dict_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_area_dict_t`;
CREATE TABLE `mooc_area_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';

-- ----------------------------
-- Records of mooc_area_dict_t
-- ----------------------------
INSERT INTO `mooc_area_dict_t` VALUES ('1', '朝阳区');
INSERT INTO `mooc_area_dict_t` VALUES ('2', '海淀区');
INSERT INTO `mooc_area_dict_t` VALUES ('3', '丰台区');
INSERT INTO `mooc_area_dict_t` VALUES ('4', '大兴区');
INSERT INTO `mooc_area_dict_t` VALUES ('5', '东城区');
INSERT INTO `mooc_area_dict_t` VALUES ('6', '西城区');
INSERT INTO `mooc_area_dict_t` VALUES ('7', '通州区');
INSERT INTO `mooc_area_dict_t` VALUES ('8', '房山区');
INSERT INTO `mooc_area_dict_t` VALUES ('9', '昌平区');
INSERT INTO `mooc_area_dict_t` VALUES ('10', '顺义区');
INSERT INTO `mooc_area_dict_t` VALUES ('11', '怀柔区');
INSERT INTO `mooc_area_dict_t` VALUES ('12', '门头沟');
INSERT INTO `mooc_area_dict_t` VALUES ('13', '石景山区');
INSERT INTO `mooc_area_dict_t` VALUES ('14', '密云区');
INSERT INTO `mooc_area_dict_t` VALUES ('15', '平谷区');
INSERT INTO `mooc_area_dict_t` VALUES ('16', '延庆区');
INSERT INTO `mooc_area_dict_t` VALUES ('99', '全部');

-- ----------------------------
-- Table structure for mooc_brand_dict_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_brand_dict_t`;
CREATE TABLE `mooc_brand_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='品牌信息表';

-- ----------------------------
-- Records of mooc_brand_dict_t
-- ----------------------------
INSERT INTO `mooc_brand_dict_t` VALUES ('1', '大地影院');
INSERT INTO `mooc_brand_dict_t` VALUES ('2', '万达影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('3', '耀莱成龙国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('4', '保利国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('5', '博纳国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('6', '金逸影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('7', '中影国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('8', 'CGV影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('9', '橙天嘉禾影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('10', '新华国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('11', '星美国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('12', '百老汇影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('13', 'UME国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('14', '幸福蓝海国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('15', '首都电影院');
INSERT INTO `mooc_brand_dict_t` VALUES ('16', '华谊兄弟影院');
INSERT INTO `mooc_brand_dict_t` VALUES ('17', '卢米埃影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('18', '沃美影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('19', '美嘉欢乐影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('20', '嘉华国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('21', '17.5影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('22', '太平洋电影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('23', 'SFC上影影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('24', '嘉美国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('25', '东都影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('26', '鲁信影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('27', '华影国际影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('28', '搜秀影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('29', '横店电影城');
INSERT INTO `mooc_brand_dict_t` VALUES ('99', '全部');

-- ----------------------------
-- Table structure for mooc_cinema_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_cinema_t`;
CREATE TABLE `mooc_cinema_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_name` varchar(50) DEFAULT NULL COMMENT '影院名称',
  `cinema_phone` varchar(50) DEFAULT NULL COMMENT '影院电话',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌编号',
  `area_id` int(11) DEFAULT NULL COMMENT '地域编号',
  `hall_ids` varchar(200) DEFAULT NULL COMMENT '包含的影厅类型,以#作为分割',
  `img_address` varchar(500) DEFAULT NULL COMMENT '影院图片地址',
  `cinema_address` varchar(200) DEFAULT NULL COMMENT '影院地址',
  `minimum_price` int(11) DEFAULT '0' COMMENT '最低票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影院信息表';

-- ----------------------------
-- Records of mooc_cinema_t
-- ----------------------------
INSERT INTO `mooc_cinema_t` VALUES ('1', '大地影院(顺义店)', '18500003333', '1', '1', '#1#3#5#6#', '/cinemas/30445282__5675168.jpg', '北京市顺义区华联金街购物中心', '60');
INSERT INTO `mooc_cinema_t` VALUES ('2', '大地影院(中关村店)', '010-58391939', '1', '2', '#1#2#3#4#', '/cinemas/30445282__5675168.jpg', '北京市中关村海龙大厦', '60');
INSERT INTO `mooc_cinema_t` VALUES ('3', '万达影院(大屯店)', '010-58391939', '2', '3', '#5#6#7#8#', '/cinemas/44374823__5777386.jpg', '北京市朝阳区大屯路50号金街商场', '60');
INSERT INTO `mooc_cinema_t` VALUES ('4', '万达影院(奥体中心店)', '010-58391231', '2', '4', '#1#3#5#6#', '/cinemas/44374823__5777386.jpg', '北京市朝阳区奥林匹克公园新奥购物中心', '60');
INSERT INTO `mooc_cinema_t` VALUES ('5', '万达影院(中南海店)', '010-58398521', '2', '5', '#1#5#7#8#', '/cinemas/44374823__5777386.jpg', '北京市东城区中南海52号', '60');
INSERT INTO `mooc_cinema_t` VALUES ('6', '万达影院(国贸店)', '010-96385274', '2', '6', '#1#2#3#7#', '/cinemas/5_0805163047.jpg', '北京市朝阳区国贸CBD核心商场5012', '60');
INSERT INTO `mooc_cinema_t` VALUES ('7', '慕课影院(大屯店)', '010-98765432', '3', '7', '#1#5#8#9#', '/cinemas/5_0805163047.jpg', '北京市朝阳区大屯路50号金街商场', '60');

-- ----------------------------
-- Table structure for mooc_field_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_field_t`;
CREATE TABLE `mooc_field_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `begin_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `hall_id` int(11) DEFAULT NULL COMMENT '放映厅类型编号',
  `hall_name` varchar(200) DEFAULT NULL COMMENT '放映厅名称',
  `price` int(11) DEFAULT NULL COMMENT '票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='放映场次表';

-- ----------------------------
-- Records of mooc_field_t
-- ----------------------------
INSERT INTO `mooc_field_t` VALUES ('1', '1', '2', '09:50', '11:20', '1', '一号厅', '60');
INSERT INTO `mooc_field_t` VALUES ('2', '1', '3', '11:50', '13:20', '2', 'IMAX厅', '60');
INSERT INTO `mooc_field_t` VALUES ('3', '1', '3', '13:50', '15:20', '3', '飞翔体验厅', '60');
INSERT INTO `mooc_field_t` VALUES ('4', '3', '2', '11:50', '13:20', '3', '7号超大厅', '60');
INSERT INTO `mooc_field_t` VALUES ('5', '3', '2', '11:50', '13:20', '4', '飞翔体验厅', '60');
INSERT INTO `mooc_field_t` VALUES ('6', '5', '2', '11:50', '13:20', '5', '3号VIP厅', '60');
INSERT INTO `mooc_field_t` VALUES ('7', '6', '2', '11:50', '13:20', '6', '5号4D厅', '60');

-- ----------------------------
-- Table structure for mooc_hall_dict_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_hall_dict_t`;
CREATE TABLE `mooc_hall_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `seat_address` varchar(200) DEFAULT NULL COMMENT '座位文件存放地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';

-- ----------------------------
-- Records of mooc_hall_dict_t
-- ----------------------------
INSERT INTO `mooc_hall_dict_t` VALUES ('1', 'IMAX厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('2', 'CGS中国巨幕厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('3', '杜比全景声厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('4', 'Dolby Cinema厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('5', 'RealD厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('6', 'RealD 6FL厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('7', 'LUXE巨幕厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('8', '4DX厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('9', 'DTS:X 临境音厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('10', '儿童厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('11', '4K厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('12', '4D厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('13', '巨幕厅', 'seats/123214.json');
INSERT INTO `mooc_hall_dict_t` VALUES ('99', '全部', null);

-- ----------------------------
-- Table structure for mooc_hall_film_info_t
-- ----------------------------
DROP TABLE IF EXISTS `mooc_hall_film_info_t`;
CREATE TABLE `mooc_hall_film_info_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `film_name` varchar(50) DEFAULT NULL COMMENT '电影名称',
  `film_length` varchar(50) DEFAULT NULL COMMENT '电影时长',
  `film_cats` varchar(200) DEFAULT NULL COMMENT '电影类型',
  `film_language` varchar(50) DEFAULT NULL COMMENT '电影语言',
  `actors` varchar(200) DEFAULT NULL COMMENT '演员列表',
  `img_address` varchar(500) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影厅电影信息表';

-- ----------------------------
-- Records of mooc_hall_film_info_t
-- ----------------------------
INSERT INTO `mooc_hall_film_info_t` VALUES ('1', '2', '我不是药神', '117', '喜剧,剧情', '国语2D', '程勇,曹斌,吕受益,刘思慧', 'films/238e2dc36beae55a71cabfc14069fe78236351.jpg');
INSERT INTO `mooc_hall_film_info_t` VALUES ('2', '3', '爱情公寓', '123', '喜剧,动作,冒险', '国语2D', '曾小贤,胡一菲,唐悠悠,张伟', 'films/238e2dc36beae55a71cabfc14069fe78236351.jpg');

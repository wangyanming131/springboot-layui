/*
 Navicat Premium Data Transfer

 Source Server         : mysql-5.7.21
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : db_springboot_layui

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 08/06/2021 15:58:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for settings_pay_type
-- ----------------------------
DROP TABLE IF EXISTS `settings_pay_type`;
CREATE TABLE `settings_pay_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式代码',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式中文名称',
  `created_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `type_status` int(1) NULL DEFAULT 0 COMMENT '状态(0:无效;1:有效)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付方式表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settings_pay_type
-- ----------------------------
INSERT INTO `settings_pay_type` VALUES (6, '02', '微信', '2021-06-03 15:55:11', NULL, 1);
INSERT INTO `settings_pay_type` VALUES (7, '03', '银行卡', '2021-06-03 16:01:51', NULL, 1);
INSERT INTO `settings_pay_type` VALUES (8, '01', '支付宝2', '2021-06-03 16:06:01', '2021-06-08 15:32:18', 1);
INSERT INTO `settings_pay_type` VALUES (11, '04', '花钱吧', '2021-06-03 16:43:31', '2021-06-03 17:02:32', 1);

SET FOREIGN_KEY_CHECKS = 1;

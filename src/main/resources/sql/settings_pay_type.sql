/*
 Navicat Premium Data Transfer

 Source Server         : 线下服务器
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 172.16.1.60:3306
 Source Schema         : tiantue

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 05/12/2018 10:56:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `settings_pay_type`;
CREATE TABLE `settings_pay_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式代码',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL  NULL COMMENT '支付方式中文名称',
  `created_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '创建时间',
  `updated_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '修改时间',
  `type_status` int(1) DEFAULT 0 COMMENT '状态(0:无效;1:有效)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付方式表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settings_pay_type
-- ----------------------------


SET FOREIGN_KEY_CHECKS = 1;

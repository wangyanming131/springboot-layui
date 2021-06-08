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

 Date: 08/06/2021 15:57:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for settings_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `settings_dictionary_type`;
CREATE TABLE `settings_dictionary_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别代码',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别中文名称',
  `created_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态(0:无效;1:有效)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settings_dictionary_type
-- ----------------------------
INSERT INTO `settings_dictionary_type` VALUES (12, 'status', '数据有效性', '2021-06-08 15:06:18', '2021-06-08 15:50:08', 1);
INSERT INTO `settings_dictionary_type` VALUES (13, 'startingType', '启动方式', '2021-06-08 15:07:17', '2021-06-08 15:31:28', 1);
INSERT INTO `settings_dictionary_type` VALUES (14, 'payType', '支付方式', '2021-06-08 15:49:45', NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;

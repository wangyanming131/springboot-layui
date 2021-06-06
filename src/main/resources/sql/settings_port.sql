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

 Date: 06/06/2021 21:19:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for settings_port
-- ----------------------------
DROP TABLE IF EXISTS `settings_port`;
CREATE TABLE `settings_port`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `value` int(5) NOT NULL COMMENT '端口号值0-65535',
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用描述',
  `link` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `starting_type` int(1) NULL DEFAULT 1 COMMENT '启动方式(1:手动;2:随机启动)',
  `created_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态(0:无效;1:有效)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '端口号统计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settings_port
-- ----------------------------
INSERT INTO `settings_port` VALUES (5, 3306, 'mysql-5.7-21', 'http', 1, '2021-06-06 08:30:20', '2021-06-06 21:05:42', 1);
INSERT INTO `settings_port` VALUES (6, 3308, 'mysql-8.0.11', '', 2, '2021-06-06 20:58:32', '2021-06-06 21:16:16', 1);

SET FOREIGN_KEY_CHECKS = 1;

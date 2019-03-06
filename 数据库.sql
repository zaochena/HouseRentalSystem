/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50636
Source Host           : 172.19.80.62:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2019-01-03 11:45:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `username` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house` (
  `h_id` int(11) DEFAULT NULL,
  `house_desc` varchar(255) DEFAULT NULL,
  `house_model` varchar(255) DEFAULT NULL,
  `house_area` varchar(255) DEFAULT NULL,
  `house_floor` varchar(255) DEFAULT NULL,
  `house_type` varchar(255) DEFAULT NULL,
  `house_price` varchar(255) DEFAULT NULL,
  `house_address` varchar(255) DEFAULT NULL,
  `house_image` varchar(255) DEFAULT NULL,
  `community_name` varchar(255) DEFAULT NULL,
  `house_linkman` varchar(255) DEFAULT NULL,
  `house_oriented` varchar(255) DEFAULT NULL,
  `house_detailes_img` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `publish_time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `o_id` int(11) DEFAULT NULL,
  `h_id` int(11) DEFAULT NULL,
  `order_time` varchar(255) DEFAULT NULL,
  `order_user` varchar(255) DEFAULT NULL,
  `house_desc` varchar(255) DEFAULT NULL,
  `house_model` varchar(255) DEFAULT NULL,
  `house_area` varchar(255) DEFAULT NULL,
  `house_floor` varchar(255) DEFAULT NULL,
  `house_type` varchar(255) DEFAULT NULL,
  `house_price` int(11) DEFAULT NULL,
  `house_address` int(11) DEFAULT NULL,
  `house_image` int(11) DEFAULT NULL,
  `community_name` int(11) DEFAULT NULL,
  `house_linkman` int(11) DEFAULT NULL,
  `house_oriented` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `u_id` int(11) DEFAULT NULL,
  `u_name` varchar(255) DEFAULT NULL,
  `u_password` varchar(255) DEFAULT NULL,
  `u_phone_number` varchar(11) DEFAULT NULL,
  `u_nickname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


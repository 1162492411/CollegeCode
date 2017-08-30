/*
Navicat MySQL Data Transfer

Source Server         : db_student
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : db_student

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-04-24 09:06:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `classID` varchar(10) NOT NULL,
  `gradeID` varchar(10) DEFAULT NULL,
  `className` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`classID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class
-- ----------------------------
INSERT INTO `tb_class` VALUES ('1', '1', '3G软件14-01');
INSERT INTO `tb_class` VALUES ('2', '1', '3G软件14-02');
INSERT INTO `tb_class` VALUES ('3', '2', '3G软件13-01');
INSERT INTO `tb_class` VALUES ('4', '2', '3G软件13-01');
INSERT INTO `tb_class` VALUES ('5', '3', '3G软件12-01');
INSERT INTO `tb_class` VALUES ('6', '3', '3G软件12-02');
INSERT INTO `tb_class` VALUES ('7', '4', '3G软件11-01');
INSERT INTO `tb_class` VALUES ('8', '4', '3G软件11-02');

-- ----------------------------
-- Table structure for tb_examkinds
-- ----------------------------
DROP TABLE IF EXISTS `tb_examkinds`;
CREATE TABLE `tb_examkinds` (
  `kindID` varchar(20) NOT NULL,
  `kindName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kindID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_examkinds
-- ----------------------------
INSERT INTO `tb_examkinds` VALUES ('1', '期中');
INSERT INTO `tb_examkinds` VALUES ('2', '期末');

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `gradeID` varchar(10) NOT NULL,
  `gradeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES ('1', '大一');
INSERT INTO `tb_grade` VALUES ('2', '大二');
INSERT INTO `tb_grade` VALUES ('3', '大三');
INSERT INTO `tb_grade` VALUES ('4', '大四');
INSERT INTO `tb_grade` VALUES ('5', '研究生');
INSERT INTO `tb_grade` VALUES ('6', '博士');

-- ----------------------------
-- Table structure for tb_grade_sub
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade_sub`;
CREATE TABLE `tb_grade_sub` (
  `stuID` varchar(10) NOT NULL,
  `stuName` varchar(50) DEFAULT NULL,
  `kindID` varchar(10) NOT NULL,
  `code` varchar(10) NOT NULL,
  `grade` float(8,0) DEFAULT NULL,
  PRIMARY KEY (`stuID`,`kindID`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_grade_sub
-- ----------------------------
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '1', '1', '80');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '1', '2', '70');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '1', '3', '60');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '1', '4', '50');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '1', '5', '40');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '1', '6', '30');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '2', '1', '20');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '2', '2', '30');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '2', '3', '20');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '2', '4', '11');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '2', '5', '0');
INSERT INTO `tb_grade_sub` VALUES ('1', '张一一', '2', '6', '0');
INSERT INTO `tb_grade_sub` VALUES ('2', '张二二', '1', '1', '211');
INSERT INTO `tb_grade_sub` VALUES ('2', '张二二', '1', '3', '213');
INSERT INTO `tb_grade_sub` VALUES ('2', '张二二', '2', '1', '221');
INSERT INTO `tb_grade_sub` VALUES ('2', '张二二', '2', '3', '223');
INSERT INTO `tb_grade_sub` VALUES ('3', '王三三', '1', '1', '50');
INSERT INTO `tb_grade_sub` VALUES ('3', '王三三', '1', '2', '40');
INSERT INTO `tb_grade_sub` VALUES ('3', '王三三', '1', '3', '30');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `stuID` varchar(10) NOT NULL,
  `classID` varchar(10) DEFAULT NULL,
  `stuName` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '1', '张一一', '男', '20', '地址一', '电话一');
INSERT INTO `tb_student` VALUES ('10', '1', '十十十', '男', '22', '地址十', '电话十');
INSERT INTO `tb_student` VALUES ('11', '1', '十一', '女', '22', '地址十一', '电话十一');
INSERT INTO `tb_student` VALUES ('2', '2', '张二二', '男', '21', '地址二', '电话二');
INSERT INTO `tb_student` VALUES ('3', '3', '王三三', '女', '20', '地址三', '电话三');
INSERT INTO `tb_student` VALUES ('4', '1', '李四四', '男', '23', '地址六', '电话六');
INSERT INTO `tb_student` VALUES ('5', '1', '李哈哈', '男', '23', '地址六', '电话六');
INSERT INTO `tb_student` VALUES ('6', '1', '李六六', '男', '23', '地址六', '电话六');
INSERT INTO `tb_student` VALUES ('7', '1', '七七七', '女', '22', '地址七', '电话七');
INSERT INTO `tb_student` VALUES ('8', '1', '巴巴吧', '男', '22', '地址八', '电话八');
INSERT INTO `tb_student` VALUES ('9', '1', '九九九', '女', '22', '地址九', '电话九');

-- ----------------------------
-- Table structure for tb_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject`;
CREATE TABLE `tb_subject` (
  `code` varchar(10) NOT NULL,
  `subject` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject
-- ----------------------------
INSERT INTO `tb_subject` VALUES ('1', '课程1');
INSERT INTO `tb_subject` VALUES ('2', '课程2');
INSERT INTO `tb_subject` VALUES ('3', '课程3');
INSERT INTO `tb_subject` VALUES ('4', '课程4');
INSERT INTO `tb_subject` VALUES ('5', '课程5');
INSERT INTO `tb_subject` VALUES ('6', '课程6');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `teaID` varchar(10) NOT NULL,
  `classID` varchar(10) DEFAULT NULL,
  `teaName` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `knowledge` varchar(20) DEFAULT NULL,
  `knowLevel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`teaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES ('1', '1', '教师一', '男', '讲师', '5');
INSERT INTO `tb_teacher` VALUES ('2', '1', '教师二', '男', '副教授', '6');
INSERT INTO `tb_teacher` VALUES ('3', '2', '教师三', '女', '副教授', '6');
INSERT INTO `tb_teacher` VALUES ('4', '2', '教师四', '男', '教授', '7');
INSERT INTO `tb_teacher` VALUES ('5', '3', '教师五', '男', '副教授', '6');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userID` varchar(50) NOT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '用户一', '123456');
INSERT INTO `tb_user` VALUES ('2', '用户二', '654321');
INSERT INTO `tb_user` VALUES ('3', '用户三', '231456');

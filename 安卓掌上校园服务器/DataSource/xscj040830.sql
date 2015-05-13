/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50015
Source Host           : localhost:3306
Source Database       : xscj

Target Server Type    : MYSQL
Target Server Version : 50015
File Encoding         : 65001

Date: 2014-09-01 21:22:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `banji`
-- ----------------------------
DROP TABLE IF EXISTS `banji`;
CREATE TABLE `banji` (
  `CID` decimal(8,0) NOT NULL COMMENT '班级编号',
  `CNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '班级名称',
  `CTYPE` int(11) NOT NULL default '1' COMMENT '1全日制班，2校园版，3周末班，4晚间班',
  `STATUS` int(2) NOT NULL default '2' COMMENT '0表示已经毕业的班级，1表示正在上课，2表示即将开的新班',
  `CINFO` varchar(300) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='存储班级信息';

-- ----------------------------
-- Records of banji
-- ----------------------------
INSERT INTO banji VALUES ('1', '计算机科学1班', '1', '1', '计算机科学');
INSERT INTO banji VALUES ('101', '全日制班', '1', '0', '全天候上课');
INSERT INTO banji VALUES ('104', '充电班', '1', '2', '半工半读');
INSERT INTO banji VALUES ('111', 'java全日制软件开发工程师班', '1', '2', '提升专业技能，让所学有所用');
INSERT INTO banji VALUES ('1100', '晚班', '1', '2', '晚班');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `KID` int(11) NOT NULL auto_increment COMMENT '课程编号',
  `KNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '课程名称',
  `KTIME` decimal(8,0) NOT NULL COMMENT '建议课时',
  `KINFO` varchar(300) collate utf8_unicode_ci default NULL COMMENT '课程描述',
  PRIMARY KEY  (`KID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='存储课程信息';

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', 'JAVASE', '52', 'java软件开发的基础。');
INSERT INTO course VALUES ('2', 'html', '30', '学习网页设计');
INSERT INTO course VALUES ('3', 'javaWeb', '40', '学习B/S架构系统。');
INSERT INTO course VALUES ('4', 'css', '10', '学习网页设计知识。');
INSERT INTO course VALUES ('5', '环球地理', '20', '讲述');
INSERT INTO course VALUES ('6', '软件开发工程师', '400', '月薪上万，那都不是事。');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `SCID` int(11) NOT NULL auto_increment COMMENT '成绩编号主键',
  `TID` decimal(8,0) NOT NULL COMMENT '教师工号用于登陆本系统',
  `SID` decimal(8,0) NOT NULL COMMENT '学生学号',
  `KID` int(11) NOT NULL COMMENT '课程编号',
  `TIMES` int(11) NOT NULL default '1' COMMENT '考试次数',
  `SCORE` decimal(8,0) NOT NULL default '0' COMMENT '分数',
  `ENABLE` decimal(2,0) NOT NULL default '0' COMMENT '0表示启用状态，1表示封存状态',
  PRIMARY KEY  (`SCID`),
  KEY `FK_SC` (`KID`),
  KEY `FK_SS` (`SID`),
  KEY `FK_ST` (`TID`),
  CONSTRAINT `FK_SC` FOREIGN KEY (`KID`) REFERENCES `course` (`KID`),
  CONSTRAINT `FK_SS` FOREIGN KEY (`SID`) REFERENCES `student` (`SID`),
  CONSTRAINT `FK_ST` FOREIGN KEY (`TID`) REFERENCES `teacher` (`TID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='存储学生的成绩信息';

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO score VALUES ('10', '9001', '2001', '1', '3', '92', '1');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `SID` decimal(8,0) NOT NULL COMMENT '学生学号',
  `SNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '学生姓名',
  `SEX` char(2) collate utf8_unicode_ci default NULL COMMENT '学生性别',
  `AGE` decimal(8,0) default NULL COMMENT '学生年龄',
  `INTIME` varchar(25) collate utf8_unicode_ci default NULL,
  `OUTIME` varchar(25) collate utf8_unicode_ci default NULL,
  `CID` decimal(8,0) default NULL COMMENT '班级编号',
  PRIMARY KEY  (`SID`),
  KEY `FK_CS` (`CID`),
  CONSTRAINT `FK_CS` FOREIGN KEY (`CID`) REFERENCES `banji` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='保存学生信息';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO student VALUES ('2001', '汪磊', '男', '22', '2014-4-8', '2014-8-20', '1');
INSERT INTO student VALUES ('2002', '张伟', '男', '24', '2014-9-1', '2014-12-30', '1');
INSERT INTO student VALUES ('2003', '黄发全', '男', '22', '2014-5-1', '2014-8-30', '1');
INSERT INTO student VALUES ('2004', '徐天明', '男', '21', '2007-9-1', '2011-6-20', '104');
INSERT INTO student VALUES ('2005', '郑洪斌', '男', '24', '2014-1-1', '2015-1-1', '111');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `TID` decimal(8,0) NOT NULL COMMENT '教师工号用于登陆本系统',
  `TNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '教师姓名',
  `TMAJOR` varchar(30) collate utf8_unicode_ci default NULL COMMENT '教师所学的专业',
  `TJOB` varchar(25) collate utf8_unicode_ci default NULL COMMENT '教师的岗位',
  PRIMARY KEY  (`TID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='存储教师信息';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO teacher VALUES ('1000', '管理员', '暂无', '暂无资料');
INSERT INTO teacher VALUES ('9001', '陈美汜', '软件工程', '教务总监');
INSERT INTO teacher VALUES ('9002', '范红', '计算机科学与技术', 'java讲师');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UID` decimal(8,0) NOT NULL COMMENT '用户编号，用于登陆系统',
  `UNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '用户名称',
  `PASSWORD` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '用户密码',
  `ROLE` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '用户角色',
  PRIMARY KEY  (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='存储用户信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1000', '管理员', '1000', 'admin');
INSERT INTO user VALUES ('2001', '汪磊', '2001', 'student');
INSERT INTO user VALUES ('2002', '张伟', '2002', 'student');
INSERT INTO user VALUES ('2003', '黄发全', '2003', 'student');
INSERT INTO user VALUES ('2004', '徐天明', '2004', 'student');
INSERT INTO user VALUES ('2005', '郑洪斌', '2005', 'student');
INSERT INTO user VALUES ('9001', '陈美汜', '9001', 'teacher');
INSERT INTO user VALUES ('9002', '范红', '9002', 'teacher');

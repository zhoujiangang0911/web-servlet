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
  `CID` decimal(8,0) NOT NULL COMMENT '�༶���',
  `CNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '�༶����',
  `CTYPE` int(11) NOT NULL default '1' COMMENT '1ȫ���ư࣬2У԰�棬3��ĩ�࣬4����',
  `STATUS` int(2) NOT NULL default '2' COMMENT '0��ʾ�Ѿ���ҵ�İ༶��1��ʾ�����ϿΣ�2��ʾ���������°�',
  `CINFO` varchar(300) collate utf8_unicode_ci default NULL,
  PRIMARY KEY  (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='�洢�༶��Ϣ';

-- ----------------------------
-- Records of banji
-- ----------------------------
INSERT INTO banji VALUES ('1', '�������ѧ1��', '1', '1', '�������ѧ');
INSERT INTO banji VALUES ('101', 'ȫ���ư�', '1', '0', 'ȫ����Ͽ�');
INSERT INTO banji VALUES ('104', '����', '1', '2', '�빤���');
INSERT INTO banji VALUES ('111', 'javaȫ���������������ʦ��', '1', '2', '����רҵ���ܣ�����ѧ������');
INSERT INTO banji VALUES ('1100', '���', '1', '2', '���');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `KID` int(11) NOT NULL auto_increment COMMENT '�γ̱��',
  `KNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '�γ�����',
  `KTIME` decimal(8,0) NOT NULL COMMENT '�����ʱ',
  `KINFO` varchar(300) collate utf8_unicode_ci default NULL COMMENT '�γ�����',
  PRIMARY KEY  (`KID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='�洢�γ���Ϣ';

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', 'JAVASE', '52', 'java��������Ļ�����');
INSERT INTO course VALUES ('2', 'html', '30', 'ѧϰ��ҳ���');
INSERT INTO course VALUES ('3', 'javaWeb', '40', 'ѧϰB/S�ܹ�ϵͳ��');
INSERT INTO course VALUES ('4', 'css', '10', 'ѧϰ��ҳ���֪ʶ��');
INSERT INTO course VALUES ('5', '�������', '20', '����');
INSERT INTO course VALUES ('6', '�����������ʦ', '400', '��н�����Ƕ������¡�');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `SCID` int(11) NOT NULL auto_increment COMMENT '�ɼ��������',
  `TID` decimal(8,0) NOT NULL COMMENT '��ʦ�������ڵ�½��ϵͳ',
  `SID` decimal(8,0) NOT NULL COMMENT 'ѧ��ѧ��',
  `KID` int(11) NOT NULL COMMENT '�γ̱��',
  `TIMES` int(11) NOT NULL default '1' COMMENT '���Դ���',
  `SCORE` decimal(8,0) NOT NULL default '0' COMMENT '����',
  `ENABLE` decimal(2,0) NOT NULL default '0' COMMENT '0��ʾ����״̬��1��ʾ���״̬',
  PRIMARY KEY  (`SCID`),
  KEY `FK_SC` (`KID`),
  KEY `FK_SS` (`SID`),
  KEY `FK_ST` (`TID`),
  CONSTRAINT `FK_SC` FOREIGN KEY (`KID`) REFERENCES `course` (`KID`),
  CONSTRAINT `FK_SS` FOREIGN KEY (`SID`) REFERENCES `student` (`SID`),
  CONSTRAINT `FK_ST` FOREIGN KEY (`TID`) REFERENCES `teacher` (`TID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='�洢ѧ���ĳɼ���Ϣ';

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO score VALUES ('10', '9001', '2001', '1', '3', '92', '1');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `SID` decimal(8,0) NOT NULL COMMENT 'ѧ��ѧ��',
  `SNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT 'ѧ������',
  `SEX` char(2) collate utf8_unicode_ci default NULL COMMENT 'ѧ���Ա�',
  `AGE` decimal(8,0) default NULL COMMENT 'ѧ������',
  `INTIME` varchar(25) collate utf8_unicode_ci default NULL,
  `OUTIME` varchar(25) collate utf8_unicode_ci default NULL,
  `CID` decimal(8,0) default NULL COMMENT '�༶���',
  PRIMARY KEY  (`SID`),
  KEY `FK_CS` (`CID`),
  CONSTRAINT `FK_CS` FOREIGN KEY (`CID`) REFERENCES `banji` (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='����ѧ����Ϣ';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO student VALUES ('2001', '����', '��', '22', '2014-4-8', '2014-8-20', '1');
INSERT INTO student VALUES ('2002', '��ΰ', '��', '24', '2014-9-1', '2014-12-30', '1');
INSERT INTO student VALUES ('2003', '�Ʒ�ȫ', '��', '22', '2014-5-1', '2014-8-30', '1');
INSERT INTO student VALUES ('2004', '������', '��', '21', '2007-9-1', '2011-6-20', '104');
INSERT INTO student VALUES ('2005', '֣���', '��', '24', '2014-1-1', '2015-1-1', '111');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `TID` decimal(8,0) NOT NULL COMMENT '��ʦ�������ڵ�½��ϵͳ',
  `TNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '��ʦ����',
  `TMAJOR` varchar(30) collate utf8_unicode_ci default NULL COMMENT '��ʦ��ѧ��רҵ',
  `TJOB` varchar(25) collate utf8_unicode_ci default NULL COMMENT '��ʦ�ĸ�λ',
  PRIMARY KEY  (`TID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='�洢��ʦ��Ϣ';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO teacher VALUES ('1000', '����Ա', '����', '��������');
INSERT INTO teacher VALUES ('9001', '������', '�������', '�����ܼ�');
INSERT INTO teacher VALUES ('9002', '����', '�������ѧ�뼼��', 'java��ʦ');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UID` decimal(8,0) NOT NULL COMMENT '�û���ţ����ڵ�½ϵͳ',
  `UNAME` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '�û�����',
  `PASSWORD` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '�û�����',
  `ROLE` varchar(25) collate utf8_unicode_ci NOT NULL COMMENT '�û���ɫ',
  PRIMARY KEY  (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='�洢�û���Ϣ';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1000', '����Ա', '1000', 'admin');
INSERT INTO user VALUES ('2001', '����', '2001', 'student');
INSERT INTO user VALUES ('2002', '��ΰ', '2002', 'student');
INSERT INTO user VALUES ('2003', '�Ʒ�ȫ', '2003', 'student');
INSERT INTO user VALUES ('2004', '������', '2004', 'student');
INSERT INTO user VALUES ('2005', '֣���', '2005', 'student');
INSERT INTO user VALUES ('9001', '������', '9001', 'teacher');
INSERT INTO user VALUES ('9002', '����', '9002', 'teacher');

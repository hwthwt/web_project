键表语句:
/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : itanynetclass

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-01-31 14:32:26
*/
drop database if exists netclass;
create database netclass;
use netclass;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论表的主键',
  `context` varchar(2000) DEFAULT NULL COMMENT '评论内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) DEFAULT NULL COMMENT '发布的用户id',
  `resource_id` int(11) DEFAULT NULL COMMENT '被评论的资源id',
  `status` int(1) DEFAULT NULL COMMENT '0启用1禁用2待审核',
  PRIMARY KEY (`id`),
  KEY `t_comment_chapter_id_fk` (`resource_id`),
  KEY `t_comment_user_id_fk` (`user_id`),
  CONSTRAINT `t_comment_resource_id_fk` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`),
  CONSTRAINT `t_comment_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程表主键',
  `course_name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_info` varchar(400) DEFAULT NULL COMMENT '课程简介',
  `author` varchar(100) DEFAULT NULL COMMENT '课程的作者',
  `cover_image_url` varchar(200) DEFAULT NULL COMMENT '课程封面图片的相对路径',
  `create_date` datetime DEFAULT NULL COMMENT '课程发布时间',
  `click_number` int(11) DEFAULT '0' COMMENT '课程点击量',
  `status` int(1) DEFAULT NULL COMMENT '课程状态(0启用,1禁用)',
  `recommendation_grade` int(1) DEFAULT NULL COMMENT '课程推荐等级(0普通,1推荐)',
  `course_type_id` int(11) DEFAULT NULL COMMENT '所属的课程类别的id',
  PRIMARY KEY (`id`),
  KEY `t_course_type_fk` (`course_type_id`),
  CONSTRAINT `t_course_type_fk` FOREIGN KEY (`course_type_id`) REFERENCES `t_course_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_chapter`
-- ----------------------------
DROP TABLE IF EXISTS `t_chapter`;
CREATE TABLE `t_chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程与资源的表的主键(课程章节)',
  `course_id` int(11) DEFAULT NULL COMMENT '课程的id',
  `title` varchar(100) DEFAULT NULL COMMENT '课程章节标题',
  `info` varchar(300) DEFAULT NULL COMMENT '课程章节简介',
  `create_date` datetime DEFAULT NULL COMMENT '课程章节创建时间',
  `status` int(1) DEFAULT NULL COMMENT '0启用1禁用',
  PRIMARY KEY (`id`),
  KEY `t_course_id_fk` (`course_id`),
  CONSTRAINT `t_chapter_course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chapter
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_course_type`;
CREATE TABLE `t_course_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(60) DEFAULT NULL COMMENT '类型名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id',
  `status` int(1) DEFAULT NULL COMMENT '0启用1禁用',
  PRIMARY KEY (`id`),
  KEY `t_type_parent_fk` (`parent_id`),
  CONSTRAINT `t_type_parent_fk` FOREIGN KEY (`parent_id`) REFERENCES `t_course_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_gold_points`
-- ----------------------------
DROP TABLE IF EXISTS `t_gold_points`;
CREATE TABLE `t_gold_points` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分金币表主键',
  `user_id` int(11) DEFAULT NULL COMMENT '积分金币所属用户id',
  `point_count` int(11) DEFAULT '0' COMMENT '使用或获得的积分数',
  `gold_count` int(11) DEFAULT '0' COMMENT '使用或获得的金币数',
  `info` varchar(200) DEFAULT NULL COMMENT '操作的内容简单说明',
  `create_date` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `t_gold_points_user_id_fk` (`user_id`),
  CONSTRAINT `t_gold_points_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gold_points
-- ----------------------------

-- ----------------------------
-- Table structure for `t_praise`
-- ----------------------------
DROP TABLE IF EXISTS `t_praise`;
CREATE TABLE `t_praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞记录表主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `comment_id` int(11) DEFAULT NULL COMMENT '被点赞的评论的id',
  `create_date` datetime DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  KEY `t_praise_comment_id_fk` (`comment_id`),
  KEY `t_praise_user_id_fk` (`user_id`),
  CONSTRAINT `t_praise_comment_id_fk` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `t_praise_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_praise
-- ----------------------------

-- ----------------------------
-- Table structure for `t_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源主键',
  `title` varchar(200) DEFAULT NULL COMMENT '资源标题',
  `path` varchar(200) DEFAULT NULL COMMENT '资源相对路径',
  `cover_image_url` varchar(200) DEFAULT NULL COMMENT '资源封面图片地址',
  `original_name` varchar(200) DEFAULT NULL COMMENT '文件原始名称',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小(字节)',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型(文件后缀名)',
  `click_count` int(11) DEFAULT NULL COMMENT '点击量',
  `create_date` datetime DEFAULT NULL COMMENT '上传时间',
  `cost_type` int(1) DEFAULT NULL COMMENT '0积分,1金币',
  `cost_number` int(11) DEFAULT '0' COMMENT '下载文件或查看视频需要的积分或金币',
  `user_id` int(11) DEFAULT NULL COMMENT '上传用户id',
  `chapter_id` int(11) DEFAULT NULL COMMENT '章节id',
  `status` int(1) DEFAULT NULL COMMENT '0启用1禁用',
  PRIMARY KEY (`id`),
  KEY `t_resource_user_id_fk` (`user_id`),
  KEY `t_resource_chapter_id_fk` (`chapter_id`),
  CONSTRAINT `t_resource_chapter_id_fk` FOREIGN KEY (`chapter_id`) REFERENCES `t_chapter` (`id`),
  CONSTRAINT `t_resource_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `login_name` varchar(40) DEFAULT NULL COMMENT '用户名,登录名',
  `nickname` varchar(60) NOT NULL COMMENT '用户昵称',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `role` varchar(60) DEFAULT NULL COMMENT '角色',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `login_date` datetime DEFAULT NULL COMMENT '最近一次登录的日期',
  `create_date` datetime DEFAULT NULL COMMENT '用户创建日期',
  `status` int(1) DEFAULT NULL COMMENT '0启用,1禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_resource`;
CREATE TABLE `t_user_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户已购买的资源表主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源id',
  `create_date` datetime DEFAULT NULL COMMENT '购买日期',
  `update_date` datetime DEFAULT NULL COMMENT '最近一次查看的日期',
  PRIMARY KEY (`id`),
  KEY `t_resource_user_user_id_fk` (`user_id`),
  KEY `t_resource_user_resource_id_fk` (`resource_id`),
  CONSTRAINT `t_resource_user_resource_id_fk` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`),
  CONSTRAINT `t_resource_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




insert into t_user (login_name,nickname,password,role,email,login_date,create_date,status) values("a","a","a","admin","a@a.com","2018-10-10 10:10:10","2018-10-10 10:10:10",0);


insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程1","课程简介","Hugo","asdds","2018-10-10 10:10:10",21,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程2","课程简介","Hugo","asdds","2018-10-10 10:10:10",20,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程3","课程简介","Hugo","asdds","2018-10-10 10:10:10",24,0,0,8);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程4","课程简介","Hugo","asdds","2018-10-10 10:10:10",200,0,0,8);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程5","课程简介","Hugo","asdds","2018-10-10 10:10:10",23,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程6","课程简介","Hugo","asdds","2018-10-10 10:10:10",27,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程7","课程简介","Hugo","asdds","2018-10-10 10:10:10",27,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程8","课程简介","Hugo","asdds","2018-10-10 10:10:10",27,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程9","课程简介","Hugo","asdds","2018-10-10 10:10:10",27,0,0,7);
insert into t_course (course_name,course_info,author,cover_image_url,create_date,click_number,status,recommendation_grade,course_type_id) values("课程10","课程简介","Hugo","asdds","2018-10-10 10:10:10",27,0,0,7);



insert into t_course_type(id,type_name,status) values (1,"1",0);
insert into t_course_type(id,type_name,status) values (2,"2",0);
insert into t_course_type(id,type_name,parent_id,status) values (3,"1-1",1,0);
insert into t_course_type(id,type_name,parent_id,status) values (4,"2-1",2,0);
insert into t_course_type(id,type_name,parent_id,status) values (5,"1-2",1,0);
insert into t_course_type(id,type_name,parent_id,status) values (6,"2-2",2,0);
insert into t_course_type(id,type_name,parent_id,status) values (7,"1-2-1",5,0);
insert into t_course_type(id,type_name,parent_id,status) values (8,"2-2-1",6,0);



insert into t_chapter(course_id,title,info,create_date,status) values (1,"章节1","info","2018-10-10 10:10:10",0);
insert into t_chapter(course_id,title,info,create_date,status) values (1,"章节2","info","2018-10-10 10:10:10",0);
insert into t_chapter(course_id,title,info,create_date,status) values (1,"章节3","info","2018-10-10 10:10:10",0);
insert into t_chapter(course_id,title,info,create_date,status) values (1,"章节4","info","2018-10-10 10:10:10",0);
insert into t_chapter(course_id,title,info,create_date,status) values (1,"章节5","info","2018-10-10 10:10:10",0);
insert into t_chapter(course_id,title,info,create_date,status) values (1,"章节6","info","2018-10-10 10:10:10",0);


insert into t_comment(context,create_date,user_id,resource_id,status) values ("不错","2018-10-10 10:10:10",1,1,0);




访问首页:
http://127.0.0.1:8080/netClass/userfont/findtypes.do
后台首页:
http://127.0.0.1:8080/netClass/back_home.do


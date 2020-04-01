/*
 Navicat Premium Data Transfer

 Source Server         : root@localhost
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 01/04/2020 18:27:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`
(
    `id`                int(11)                                                       NOT NULL AUTO_INCREMENT,
    `username`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `password`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `email`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `bio`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `favourite_section` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author`
VALUES (1, 'chan', '123456', 'chan@mybatis.com', 'java开发', '技术');

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`
(
    `id`        int(11)                                                       NOT NULL AUTO_INCREMENT,
    `title`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `author_id` int(11)                                                       NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_author_id` (`author_id`) USING BTREE,
    CONSTRAINT `fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog`
VALUES (4, 'java从入门到放弃', 1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`      int(11)                                                       NOT NULL AUTO_INCREMENT,
    `post_id` int(11)                                                       NULL DEFAULT NULL,
    `name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_comment_post_id` (`post_id`) USING BTREE,
    CONSTRAINT `fk_comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment`
VALUES (1, 1, '路人甲', 'java好难啊');
INSERT INTO `comment`
VALUES (2, 1, '小乙', '点赞');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`
(
    `id`         int(11)                                                       NOT NULL,
    `blog_id`    int(11)                                                       NULL DEFAULT NULL,
    `author_id`  int(11)                                                       NULL DEFAULT NULL,
    `created_on` datetime(0)                                                   NULL DEFAULT NULL,
    `section`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `subject`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `draft`      int(11)                                                       NULL DEFAULT NULL,
    `body`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_post_author_id` (`author_id`) USING BTREE,
    INDEX `fk_post_blog_id` (`blog_id`) USING BTREE,
    CONSTRAINT `fk_post_author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `fk_post_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post`
VALUES (1, 4, 1, '2020-03-31 13:16:48', 'java', 'javase', 1, 'javase教程');
INSERT INTO `post`
VALUES (2, 4, 1, '2020-03-31 13:36:06', 'java', 'javaweb', 2, 'javaweb教程');
INSERT INTO `post`
VALUES (3, 4, 1, '2020-03-31 13:36:40', 'java', 'javaee', 3, 'javaee教程');

-- ----------------------------
-- Table structure for post_tag
-- ----------------------------
DROP TABLE IF EXISTS `post_tag`;
CREATE TABLE `post_tag`
(
    `post_id` int(11) NULL DEFAULT NULL,
    `tag_id`  int(11) NULL DEFAULT NULL,
    INDEX `fk_post_tag_tag_id` (`tag_id`) USING BTREE,
    INDEX `fk_post_tag_post_id` (`post_id`) USING BTREE,
    CONSTRAINT `fk_post_tag_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `fk_post_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_tag
-- ----------------------------
INSERT INTO `post_tag`
VALUES (1, 1);
INSERT INTO `post_tag`
VALUES (1, 2);
INSERT INTO `post_tag`
VALUES (1, 3);
INSERT INTO `post_tag`
VALUES (2, 1);
INSERT INTO `post_tag`
VALUES (2, 2);
INSERT INTO `post_tag`
VALUES (2, 4);
INSERT INTO `post_tag`
VALUES (3, 1);
INSERT INTO `post_tag`
VALUES (3, 2);
INSERT INTO `post_tag`
VALUES (3, 5);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
    `id`   int(11)                                                       NOT NULL AUTO_INCREMENT,
    `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag`
VALUES (1, '编程');
INSERT INTO `tag`
VALUES (2, 'java');
INSERT INTO `tag`
VALUES (3, 'javase');
INSERT INTO `tag`
VALUES (4, 'javaweb');
INSERT INTO `tag`
VALUES (5, 'javaee');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11)                                                       NOT NULL AUTO_INCREMENT,
    `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', '123456');
INSERT INTO `user`
VALUES (4, 'test0', 'qwerty');
INSERT INTO `user`
VALUES (5, 'test1', 'qwerty');
INSERT INTO `user`
VALUES (6, 'test2', 'qwerty');
INSERT INTO `user`
VALUES (7, 'chan', 'qwerty');
INSERT INTO `user`
VALUES (8, 'chan', '123456');

SET FOREIGN_KEY_CHECKS = 1;

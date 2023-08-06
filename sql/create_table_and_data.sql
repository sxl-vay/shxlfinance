-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (x86_64)
--
-- Host: localhost    Database: yuapi
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;



create
database myapp;

use
myapp;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`           bigint                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `userAccount`  varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
    `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `unionId`      varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '微信开放平台id',
    `mpOpenId`     varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '公众号openId',
    `userName`     varchar(256) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户昵称',
    `userAvatar`   varchar(1024) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '用户头像',
    `userProfile`  varchar(512) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '用户简介',
    `userRole`     varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
    `createTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`   datetime                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`     tinyint                                 NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`),
    KEY            `idx_unionId` (`unionId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1643945886948028418
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1643945886948028417, 'shxl', 'b0dd3697a192885d7c055db46155b26a', NULL, NULL, NULL, NULL, NULL, 'user',
        '2023-04-06 19:56:57', '2023-04-09 19:56:57', 0, '1', '1');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Dumping routines for database 'yuapi'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;


-- Dump completed on 2023-06-18 15:00:41
create table bookkeeping_book
(
    `id`              bigint   NOT NULL primary key AUTO_INCREMENT COMMENT 'id',
    `userId`          bigint   NOT NULL,
    `createTime`      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleteType`      tinyint  NOT NULL DEFAULT '0' COMMENT '是否删除',
    zfbYue            decimal(18, 4)    default 0,
    zfbFund           decimal(18, 4)    default 0,
    fund              decimal(18, 4)    default 0,
    shares            decimal(18, 4)    default 0,
    bond              decimal(18, 4)    default 0,
    constructionBank  decimal(18, 4)    default 0,
    agriculturalBank  decimal(18, 4)    default 0,
    debt              decimal(18, 4)    default 0,
    wechatYue         decimal(18, 4)    default 0,
    wechatFund        decimal(18, 4)    default 0,
    merchantsBank     decimal(18, 4)    default 0,
    transferPayment   decimal(18, 4)    default 0,
    creditCardArrears decimal(18, 4)    default 0
);

create table deposit_info
(
    `id`         bigint   NOT NULL primary key AUTO_INCREMENT COMMENT 'id',
    `userId`     bigint   NOT NULL,
    `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `startTime`  datetime NOT NULL COMMENT '存款时间',
    `endTime`    datetime NOT NULL COMMENT '到期时间',
    tips         text COMMENT '备注 ',
    amount       decimal(18, 4)    default 0,
    `deleteType` tinyint  NOT NULL DEFAULT '0' COMMENT '是否删除',
    cardType     int      NOT NULL,
    remindType   int      NOT NULL
);
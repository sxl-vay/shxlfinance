CREATE TABLE `interface_info` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `name` varchar(256) NOT NULL,
                                  `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                  `url` varchar(512) NOT NULL,
                                  `requestHeader` text NOT NULL,
                                  `responseHeader` text NOT NULL,
                                  `status` int NOT NULL DEFAULT '0',
                                  `method` int NOT NULL DEFAULT '0',
                                  `userid` bigint NOT NULL DEFAULT '0',
                                  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  `isDelete` tinyint NOT NULL DEFAULT '0',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1643993747678928899 DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `post` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `title` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
                        `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
                        `tags` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签列表（json 数组）',
                        `thumbNum` int NOT NULL DEFAULT '0' COMMENT '点赞数',
                        `favourNum` int NOT NULL DEFAULT '0' COMMENT '收藏数',
                        `userId` bigint NOT NULL COMMENT '创建用户 id',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`),
                        KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1643991091887554563 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='帖子';

CREATE TABLE `post_favour` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `postId` bigint NOT NULL COMMENT '帖子 id',
                               `userId` bigint NOT NULL COMMENT '创建用户 id',
                               `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               KEY `idx_postId` (`postId`),
                               KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='帖子收藏';

CREATE TABLE `post_thumb` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `postId` bigint NOT NULL COMMENT '帖子 id',
                              `userId` bigint NOT NULL COMMENT '创建用户 id',
                              `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`),
                              KEY `idx_postId` (`postId`),
                              KEY `idx_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='帖子点赞';

CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                        `userAccount` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
                        `userPassword` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
                        `unionId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信开放平台id',
                        `mpOpenId` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公众号openId',
                        `userName` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
                        `userAvatar` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
                        `userProfile` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户简介',
                        `userRole` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        `accessKey` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `secretkey` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `idx_unionId` (`unionId`)
) ENGINE=InnoDB AUTO_INCREMENT=1643945886948028418 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';
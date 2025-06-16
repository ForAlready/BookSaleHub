-- 创建数据库
CREATE DATABASE IF NOT EXISTS booksalehub DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE booksalehub;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `email` VARCHAR(100) COMMENT '邮箱',
    `phone` VARCHAR(20) COMMENT '手机号',
    `avatar` VARCHAR(255) COMMENT '头像URL',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 图书分类表
CREATE TABLE IF NOT EXISTS `category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
    `description` VARCHAR(255) COMMENT '分类描述',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父分类ID',
    `sort` INT DEFAULT 0 COMMENT '排序',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书分类表';

-- 图书表
CREATE TABLE IF NOT EXISTS `book` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `title` VARCHAR(100) NOT NULL COMMENT '图书名称',
    `author` VARCHAR(100) NOT NULL COMMENT '作者',
    `isbn` VARCHAR(20) NOT NULL COMMENT 'ISBN',
    `publisher` VARCHAR(100) COMMENT '出版社',
    `publish_date` DATETIME COMMENT '出版日期',
    `price` DECIMAL(10,2) NOT NULL COMMENT '价格',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '库存',
    `cover` VARCHAR(255) COMMENT '封面图片URL',
    `description` TEXT COMMENT '图书简介',
    `category_id` BIGINT NOT NULL COMMENT '分类ID',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-下架，1-上架',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_isbn` (`isbn`),
    KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表';

-- 插入测试数据
INSERT INTO `user` (`username`, `password`, `email`, `status`) VALUES
('admin', '$2a$10$X/hX4Jz7Y5Y5Y5Y5Y5Y5Y.5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y5Y', 'admin@booksalehub.com', 1);

INSERT INTO `category` (`name`, `description`, `parent_id`, `sort`) VALUES
('文学', '文学类图书', 0, 1),
('计算机', '计算机类图书', 0, 2),
('历史', '历史类图书', 0, 3),
('小说', '小说类图书', 1, 1),
('诗歌', '诗歌类图书', 1, 2),
('编程', '编程类图书', 2, 1),
('数据库', '数据库类图书', 2, 2);

INSERT INTO `book` (`title`, `author`, `isbn`, `publisher`, `publish_date`, `price`, `stock`, `category_id`, `status`) VALUES
('Java编程思想', 'Bruce Eckel', '9787111213826', '机械工业出版社', '2007-06-01', 108.00, 100, 6, 1),
('深入理解计算机系统', 'Randal E. Bryant', '9787111321330', '机械工业出版社', '2011-01-01', 139.00, 50, 6, 1),
('MySQL技术内幕', '姜承尧', '9787111405078', '机械工业出版社', '2013-05-01', 79.00, 80, 7, 1); 
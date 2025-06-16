package com.booksalehub.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 图书实体类
 */
@Data
@TableName("book")
public class Book {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 图书ISBN编号
     */
    private String isbn;

    /**
     * 图书名称
     */
    private String title;

    /**
     * 图书作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 出版日期
     */
    private LocalDateTime publishDate;

    /**
     * 图书价格
     */
    private BigDecimal price;

    /**
     * 图书库存
     */
    private Integer stock;

    /**
     * 图书封面图片URL
     */
    private String cover;

    /**
     * 图书简介
     */
    private String description;

    /**
     * 图书状态：0-下架，1-上架
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 图书分类ID
     */
    private Long categoryId;

    /**
     * 逻辑删除标记
     */
    @TableLogic
    private Integer deleted;
}
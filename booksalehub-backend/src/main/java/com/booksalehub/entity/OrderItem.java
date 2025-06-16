package com.booksalehub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单项实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单ID
     */
    @Column(nullable = false)
    private Long orderId;

    /**
     * 订单编号
     */
    @Column(nullable = false, length = 50)
    private String orderNo;

    /**
     * 用户ID
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 图书ID
     */
    @Column(nullable = false)
    private Long bookId;

    /**
     * 图书名称
     */
    @Column(nullable = false, length = 100)
    private String bookTitle;

    /**
     * 图书封面图片URL
     */
    @Column(length = 255)
    private String bookCoverUrl;

    /**
     * 图书单价
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal bookPrice;

    /**
     * 购买数量
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * 总金额
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
package com.booksalehub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单实体类
 */
@Data
@TableName("orders")
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单总金额
     */
    private Double totalAmount;

    /**
     * 订单状态：0-待付款，1-已付款，2-已取消
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 支付流水号
     */
    @Column(length = 100)
    private String paymentNo;

    /**
     * 支付时间
     */
    @Column
    private LocalDateTime payTime;

    /**
     * 订单状态：0-待付款，1-已付款，2-已发货，3-已完成，4-已取消，5-已退款
     */
    @Column(nullable = false)
    private Integer status;

    /**
     * 收货人姓名
     */
    @Column(length = 50)
    private String receiverName;

    /**
     * 收货人电话
     */
    @Column(length = 20)
    private String receiverPhone;

    /**
     * 收货地址
     */
    @Column(length = 255)
    private String receiverAddress;

    /**
     * 订单备注
     */
    @Column(length = 255)
    private String remark;

    /**
     * 发货时间
     */
    @Column
    private LocalDateTime shipTime;

    /**
     * 确认收货时间
     */
    @Column
    private LocalDateTime confirmTime;

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
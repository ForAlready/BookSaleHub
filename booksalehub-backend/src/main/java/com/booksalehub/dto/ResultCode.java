package com.booksalehub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 失败
     */
    FAILURE(500, "操作失败"),

    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),

    /**
     * 禁止访问
     */
    FORBIDDEN(403, "禁止访问"),

    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),

    /**
     * 请求方法不支持
     */
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(1001, "用户名或密码错误"),

    /**
     * 用户不存在
     */
    USER_NOT_FOUND(1002, "用户不存在"),

    /**
     * 用户已存在
     */
    USER_ALREADY_EXISTS(1003, "用户已存在"),

    /**
     * 图书不存在
     */
    BOOK_NOT_FOUND(2001, "图书不存在"),

    /**
     * 图书库存不足
     */
    BOOK_STOCK_NOT_ENOUGH(2002, "图书库存不足"),

    /**
     * 购物车为空
     */
    CART_EMPTY(3001, "购物车为空"),

    /**
     * 购物车项不存在
     */
    CART_ITEM_NOT_FOUND(3002, "购物车项不存在"),

    /**
     * 订单不存在
     */
    ORDER_NOT_FOUND(4001, "订单不存在"),

    /**
     * 订单状态错误
     */
    ORDER_STATUS_ERROR(4002, "订单状态错误"),

    /**
     * 订单已支付
     */
    ORDER_ALREADY_PAID(4003, "订单已支付"),

    /**
     * 订单未支付
     */
    ORDER_NOT_PAID(4004, "订单未支付"),

    /**
     * 支付失败
     */
    PAYMENT_FAILED(5001, "支付失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;
}
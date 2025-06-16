package com.booksalehub.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.booksalehub.common.Result;
import com.booksalehub.entity.Order;
import com.booksalehub.entity.OrderDetail;
import com.booksalehub.entity.Cart;
import com.booksalehub.entity.Book;
import com.booksalehub.service.OrderService;
import com.booksalehub.service.OrderDetailService;
import com.booksalehub.service.CartService;
import com.booksalehub.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public Result<Page<Order>> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        // TODO: 从当前登录用户获取用户ID
        Long userId = 1L;
        Page<Order> page = new Page<>(current, size);
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId)
               .orderByDesc(Order::getCreateTime);
        return Result.success(orderService.page(page, wrapper));
    }

    @GetMapping("/{id}")
    public Result<Order> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        // 获取订单详情
        LambdaQueryWrapper<OrderDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDetail::getOrderId, id);
        List<OrderDetail> details = orderDetailService.list(wrapper);
        order.setOrderDetails(details);
        return Result.success(order);
    }

    @PostMapping
    @Transactional
    public Result<Order> create() {
        // TODO: 从当前登录用户获取用户ID
        Long userId = 1L;
        
        // 获取购物车商品
        LambdaQueryWrapper<Cart> cartWrapper = new LambdaQueryWrapper<>();
        cartWrapper.eq(Cart::getUserId, userId);
        List<Cart> cartItems = cartService.list(cartWrapper);
        
        if (cartItems.isEmpty()) {
            return Result.error("购物车为空");
        }
        
        // 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(0); // 待支付
        order.setTotalAmount(0.0);
        orderService.save(order);
        
        // 创建订单详情并计算总金额
        double totalAmount = 0.0;
        for (Cart cart : cartItems) {
            Book book = bookService.getById(cart.getBookId());
            if (book == null) {
                throw new RuntimeException("商品不存在");
            }
            if (book.getStock() < cart.getQuantity()) {
                throw new RuntimeException("商品库存不足");
            }
            
            // 创建订单详情
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(order.getId());
            detail.setBookId(book.getId());
            detail.setBookTitle(book.getTitle());
            detail.setPrice(book.getPrice());
            detail.setQuantity(cart.getQuantity());
            orderDetailService.save(detail);
            
            // 更新库存
            book.setStock(book.getStock() - cart.getQuantity());
            bookService.updateById(book);
            
            // 累加总金额
            totalAmount += book.getPrice() * cart.getQuantity();
        }
        
        // 更新订单总金额
        order.setTotalAmount(totalAmount);
        orderService.updateById(order);
        
        // 清空购物车
        cartService.remove(cartWrapper);
        
        return Result.success(order);
    }

    @PutMapping("/{id}/status")
    public Result<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam Integer status) {
        Order order = orderService.getById(id);
        if (order == null) {
            return Result.error("订单不存在");
        }
        
        order.setStatus(status);
        orderService.updateById(order);
        return Result.success(order);
    }
} 
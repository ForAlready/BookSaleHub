package com.booksalehub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booksalehub.entity.Order;
import com.booksalehub.mapper.OrderMapper;
import com.booksalehub.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
} 
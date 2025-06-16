package com.booksalehub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booksalehub.entity.OrderDetail;
import com.booksalehub.mapper.OrderDetailMapper;
import com.booksalehub.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
} 
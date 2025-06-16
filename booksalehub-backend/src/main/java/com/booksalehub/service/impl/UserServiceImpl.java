package com.booksalehub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booksalehub.entity.User;
import com.booksalehub.mapper.UserMapper;
import com.booksalehub.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
} 
package com.booksalehub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.booksalehub.entity.Category;
import com.booksalehub.mapper.CategoryMapper;
import com.booksalehub.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
} 
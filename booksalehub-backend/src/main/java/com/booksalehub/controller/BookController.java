package com.booksalehub.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.booksalehub.common.Result;
import com.booksalehub.entity.Book;
import com.booksalehub.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Result<Page<Book>> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortOrder) {
        
        Page<Book> page = new Page<>(current, size);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        
        // 搜索条件
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.like(Book::getTitle, keyword)
                  .or()
                  .like(Book::getAuthor, keyword)
                  .or()
                  .like(Book::getIsbn, keyword);
        }
        
        // 分类筛选
        if (categoryId != null) {
            wrapper.eq(Book::getCategoryId, categoryId);
        }
        
        // 排序
        if (sortField != null && !sortField.isEmpty()) {
            boolean isAsc = "ascend".equals(sortOrder);
            wrapper.orderBy(true, isAsc, Book::getPrice);
        } else {
            wrapper.orderByDesc(Book::getCreateTime);
        }
        
        // 只查询上架的图书
        wrapper.eq(Book::getStatus, 1);
        
        return Result.success(bookService.page(page, wrapper));
    }

    @GetMapping("/{id}")
    public Result<Book> getById(@PathVariable Long id) {
        Book book = bookService.getById(id);
        if (book == null || book.getStatus() != 1) {
            return Result.error("图书不存在或已下架");
        }
        return Result.success(book);
    }
} 
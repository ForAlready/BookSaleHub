package com.booksalehub.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.booksalehub.common.Result;
import com.booksalehub.entity.Cart;
import com.booksalehub.entity.Book;
import com.booksalehub.service.CartService;
import com.booksalehub.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public Result<List<Cart>> list() {
        // TODO: 从当前登录用户获取用户ID
        Long userId = 1L;
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId);
        List<Cart> cartList = cartService.list(wrapper);
        
        // 获取每个商品的详细信息
        for (Cart cart : cartList) {
            Book book = bookService.getById(cart.getBookId());
            if (book != null) {
                cart.setBookTitle(book.getTitle());
                cart.setPrice(book.getPrice());
                cart.setStock(book.getStock());
            }
        }
        
        return Result.success(cartList);
    }

    @PostMapping
    public Result<Cart> add(@RequestBody Cart cart) {
        // TODO: 从当前登录用户获取用户ID
        cart.setUserId(1L);
        
        // 检查商品库存
        Book book = bookService.getById(cart.getBookId());
        if (book == null) {
            return Result.error("商品不存在");
        }
        if (book.getStock() <= 0) {
            return Result.error("商品库存不足");
        }
        
        // 检查购物车是否已存在该商品
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, cart.getUserId())
               .eq(Cart::getBookId, cart.getBookId());
        Cart existingCart = cartService.getOne(wrapper);
        
        if (existingCart != null) {
            // 如果已存在，检查更新后的数量是否超过库存
            int newQuantity = existingCart.getQuantity() + cart.getQuantity();
            if (newQuantity > book.getStock()) {
                return Result.error("商品库存不足");
            }
            // 更新数量
            existingCart.setQuantity(newQuantity);
            cartService.updateById(existingCart);
            return Result.success(existingCart);
        } else {
            // 如果不存在，检查数量是否超过库存
            if (cart.getQuantity() > book.getStock()) {
                return Result.error("商品库存不足");
            }
            // 新增
            cartService.save(cart);
            return Result.success(cart);
        }
    }

    @PutMapping("/{id}")
    public Result<Cart> update(@PathVariable Long id, @RequestBody Cart cart) {
        // 检查商品库存
        Book book = bookService.getById(cart.getBookId());
        if (book == null) {
            return Result.error("商品不存在");
        }
        if (cart.getQuantity() > book.getStock()) {
            return Result.error("商品库存不足");
        }
        
        cart.setId(id);
        cartService.updateById(cart);
        return Result.success(cart);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        cartService.removeById(id);
        return Result.success(null);
    }

    @DeleteMapping("/clear")
    public Result<Void> clear() {
        // TODO: 从当前登录用户获取用户ID
        Long userId = 1L;
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId);
        cartService.remove(wrapper);
        return Result.success(null);
    }
} 
package com.booksalehub.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.booksalehub.common.Result;
import com.booksalehub.entity.User;
import com.booksalehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User loginUser) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, loginUser.getUsername())
               .eq(User::getPassword, loginUser.getPassword());
        User user = userService.getOne(wrapper);
        
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        
        // TODO: 生成JWT token
        String token = "dummy-token";
        
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        
        return Result.success(data);
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        if (userService.count(wrapper) > 0) {
            return Result.error("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, user.getEmail());
        if (userService.count(wrapper) > 0) {
            return Result.error("邮箱已被注册");
        }
        
        // 保存用户
        userService.save(user);
        return Result.success(user);
    }

    @GetMapping("/current-user")
    public Result<User> getCurrentUser() {
        // TODO: 从JWT token中获取用户ID
        Long userId = 1L;
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @PostMapping("/logout")
    public Result<Void> logout() {
        // TODO: 清除JWT token
        return Result.success(null);
    }
} 
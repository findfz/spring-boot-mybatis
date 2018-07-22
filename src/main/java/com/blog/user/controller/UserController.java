package com.blog.user.controller;

import com.blog.user.entity.User;
import com.blog.user.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：xiongzhan
 * Description：用户操作
 * Date: 2018-07-22 15:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByPage")
    public PageInfo<User> findByPage(int pageNo,int pageSize){
        Page<User> userPage = userService.findByPage(pageNo,pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userPage);
        return pageInfo;
    }
}

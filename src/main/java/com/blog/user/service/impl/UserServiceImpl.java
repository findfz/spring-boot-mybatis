package com.blog.user.service.impl;

import com.blog.user.entity.User;
import com.blog.user.mapper.UserMapper;
import com.blog.user.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：xiongzhan
 * Description：用户接口实现
 * Date: 2018-07-22 15:23
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询
     * @param pageNo 当前页数
     * @param pageSize 分页条数
     * @return
     */
    @Override
    public Page<User> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userMapper.findByPage();
    }

}

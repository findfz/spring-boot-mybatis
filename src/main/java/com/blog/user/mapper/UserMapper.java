package com.blog.user.mapper;

import com.blog.user.entity.User;
import com.blog.util.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> findByPage();

}
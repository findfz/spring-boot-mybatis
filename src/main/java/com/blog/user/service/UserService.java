package com.blog.user.service;

import com.blog.user.entity.User;
import com.github.pagehelper.Page;

/**
 * Author：xiongzhan
 * Description：用户接口
 * Date: 2018-07-22 15:20
 */
public interface UserService {

    /**
     * 分页查询
     * @param pageNo 当前页数
     * @param pageSize 分页条数
     * @return
     */
    Page<User> findByPage(int pageNo, int pageSize);

}

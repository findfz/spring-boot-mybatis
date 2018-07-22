package com.blog.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Author：xiongzhan
 * Description：BaseMapper
 * Date: 2018-07-22 15:02
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

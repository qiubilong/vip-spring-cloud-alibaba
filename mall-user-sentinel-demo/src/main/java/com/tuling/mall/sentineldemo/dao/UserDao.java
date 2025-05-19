package com.tuling.mall.sentineldemo.dao;

import com.tuling.mall.sentineldemo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
@Mapper
public interface UserDao {

    @Select("select * from t_user where id=#{id}")
    UserEntity getById(Integer id);

}

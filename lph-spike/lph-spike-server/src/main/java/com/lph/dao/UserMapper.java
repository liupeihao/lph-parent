package com.lph.dao;


import com.lph.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *  Dao 接口
 *
 *
 * @author LPH
 * @since 2020-10-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
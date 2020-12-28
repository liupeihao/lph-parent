package com.lph.spike.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lph.spike.entity.User;
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
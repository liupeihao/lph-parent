package com.lph.service.impl;


import com.lph.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lph.entity.User;
import com.lph.dao.UserMapper;
import com.lph.service.UserService;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{


    @Autowired
    private UserMapper userMapper;


    @Override
    public void createUser(UserDto dto) {
        User user = User.createUser(dto.getName(), LocalDateTime.now(), dto.getLinkPhone(), dto.getFlag());
        userMapper.insert(user);
    }

}

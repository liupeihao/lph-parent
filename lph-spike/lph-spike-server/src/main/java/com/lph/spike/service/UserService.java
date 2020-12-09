package com.lph.spike.service;

import com.lph.spike.dto.UserDto;

/**
 *  Service
 * @author LPH
 * @since 2020-10-10
 */
public interface UserService {


    /**
     * 创建用户
     * @param dto
     */
    void createUser(UserDto dto);
}

package com.lph.spike.controller;

import com.lph.spike.dto.UserDto;
import com.lph.common.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import com.lph.spike.service.UserService;

/**
 * @author LPH
 * @since 2020-10-10
 * UserController
 * @Description
 */
@Slf4j
@RestController
@RequestMapping(value="/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping(value = "create_user")
    private ServerResponse createUser(UserDto dto){
        userService.createUser(dto);
        return ServerResponse.success();
    }


    @PostMapping(value = "discovery_test")
    private ServerResponse discoveryTest(String name){
        return ServerResponse.success(name);
    }


}
package com.lph.spike.controller;

import com.lph.common.response.across.AcrossServiceResponse;
import com.lph.common.response.form.ServerResponse;
import com.lph.spike.dto.UserDto;
import com.lph.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


/**
 * @author LPH
 * @since 2020-10-10
 * UserController
 * @Description
 */
@Slf4j
@RestController
@RequestMapping(value="/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ResponseBody
public class SpikeUserController {

    @Autowired
    public UserService userService;


    @PostMapping(value = "create_user")

    private ServerResponse createUser(UserDto dto){
        userService.createUser(dto);
        return ServerResponse.success();
    }


    @PostMapping(value = "fegin_test",produces =MediaType.APPLICATION_JSON_UTF8_VALUE )
    private AcrossServiceResponse<String> feginTest(@RequestBody String name){
        log.info("request in ,paramer is {}",name);
        return AcrossServiceResponse.success(name);
    }


}
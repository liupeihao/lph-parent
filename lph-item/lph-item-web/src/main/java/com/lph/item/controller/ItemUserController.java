package com.lph.item.controller;

import com.lph.common.response.form.ServerResponse;
import com.lph.item.service.IItemUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LPH
 * @since 2020-10-10
 * UserController
 * @Description
 */
@Api(value = "用户Controller")
@Slf4j
@RestController
@RequestMapping(value="/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ItemUserController {


    @Autowired
    private IItemUserService itemUserService;

    @PostMapping(value = "user_test")
    private ServerResponse<String> userTest(String name){
        String r_name=itemUserService.feginTest(name);

        return ServerResponse.success(r_name);
    }


}
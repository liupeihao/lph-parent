package com.lph.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lph.base.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author LPH
 * @since 2020-10-10
 */
@Data
public class UserDto extends BaseDto {



    /**
     * 主键
     */
    private Integer id;


    /**
     * 姓名
     */
    private String name;


    /**
     * 联系电话
     */
    private String linkPhone;


    /**
     * 标识：1会员；2游客
     */
    private Integer flag;

}
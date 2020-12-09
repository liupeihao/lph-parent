package com.lph.spike.dto;


import com.lph.common.base.dto.BaseDto;
import lombok.Data;

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
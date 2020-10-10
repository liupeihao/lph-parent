package com.lph.vo;


import java.time.LocalDateTime;

public class LphUserVo {

    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 联系电话
     */
    private String linkPhone;

    /**
     * 标识：1会员；2游客
     */
    private Integer flag;

}

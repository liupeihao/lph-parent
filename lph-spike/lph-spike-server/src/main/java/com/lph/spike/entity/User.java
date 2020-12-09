package com.lph.spike.entity;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lph.common.base.entity.BaseEntity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * @author LPH
 * @since 2020-10-10
 */
@Data
@JsonSerialize
public class User  extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(value="id")
    private String id;


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


    public static User createUser(String name,LocalDateTime birthday,String linkPhone,Integer flag){
        User user=new User();
        user.setId(IdWorker.getIdStr());
        user.setName(name);
        user.setBirthday(birthday);
        user.setLinkPhone(linkPhone);
        user.setFlag(flag);
        return user;
    }




}
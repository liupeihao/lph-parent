package com.lph.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDateTime;


}

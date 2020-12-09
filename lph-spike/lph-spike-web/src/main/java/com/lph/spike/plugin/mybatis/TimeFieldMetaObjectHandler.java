package com.lph.spike.plugin.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * MyBatis 自动插入更新时间和创建时间
 */
@Slf4j
@Component
public class TimeFieldMetaObjectHandler implements MetaObjectHandler {

    final String CREATEDATETIME="createDateTime";

    final String UPDATEDATETIME="modifyDateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(CREATEDATETIME, LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATEDATETIME, LocalDateTime.now(), metaObject);
    }
}

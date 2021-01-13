package com.lph.common.base.emuns;

/**
 * 需要前端接收参数的enum需要继承此枚举
 * @param <T>
 */
public interface BaseEnum<T> {

    T getId();
}

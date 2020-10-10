package com.lph.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConvertUtils {

    /**
     * 对象属性拷贝
     * PS:注意这里的不能使用内部类进行对象属性的数值拷贝
     *
     * @param source source源对象
     * @param tClass target目标对象类
     * @param <S>    源对象
     * @param <T>    目标对象
     * @return 将源对象进行属性value拷贝
     */
    @SneakyThrows({IllegalAccessException.class, InstantiationException.class})
    public static <S, T> T beanCopy(S source, Class<T> tClass) {
        T target = tClass.newInstance();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 将list源对象转换为目标对象list
     *
     * @param sourceList 源list对象
     * @param tClass     目标对象
     * @param <S>        源对象
     * @param <T>        目标对象
     * @return 目标对象list集合
     */
    public static <S, T> List<T> beansInListCopy(List<S> sourceList, Class<T> tClass) {
        return sourceList.stream()
                .map(source -> {
                    T target = null;
                    try {
                        target = tClass.newInstance();
                        BeanUtils.copyProperties(source, target);
                    }
                    catch (InstantiationException | IllegalAccessException e) {
                        return null;
                    }
                    return target;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


}

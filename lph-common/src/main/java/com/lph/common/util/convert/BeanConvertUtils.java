package com.lph.common.util.convert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lph.common.util.date.DateUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class BeanConvertUtils {

    private  static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS));
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,true);//大小写脱敏 默认为false  需要改为tru
    }


    /**
     * 两个对象属性拷贝
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
     * 将源对象list转换为目标对象list
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
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("bean in list copy error:{}", e.getMessage());
                    }
                    return target;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    /**
     * 将实体转换为Map对象
     *
     * @param source 源对象
     * @return
     * @author LPH
     * @date 2020年9月14日11:11:47
     */
    public static Map<String, String> beanToMap(Object source) {
        Map<String, String> map = new HashMap();
        for (Field field : source.getClass().getDeclaredFields()) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(source);
                map.put(field.getName(), o.toString());
                field.setAccessible(flag);
            } catch (Exception e) {
                log.error("beanToMap error:{}", e.getMessage());
            }
        }
        return map;
    }


    /**
     * 将实体转化为json
     *
     * @param source 源对象
     * @return
     * @author LPH
     * @date 2020年9月14日11:12:52
     */
    public static String beanToJson(Object source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            log.error("jscksonClient error:{}", e.getMessage());
            return new String();
        }
    }


    /**
     * 将json字符串转化为对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("jsonStringToObject error:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param clazz 目标对象Class
     * @return
     */
    public static <T> List<T> jsonArrayToList(final String json, Class<T> clazz) {
        try {
            JavaType jsonType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            return (List<T>) objectMapper.readValue(json, jsonType);
        } catch (JsonParseException e) {
            log.error("decode(String, JsonTypeReference<T>)", e);
        } catch (JsonMappingException e) {
            log.error("decode(String, JsonTypeReference<T>)", e);
        } catch (IOException e) {
            log.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }

}

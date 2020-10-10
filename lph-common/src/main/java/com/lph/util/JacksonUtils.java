package com.lph.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  序列化工具类
 */
public class JacksonUtils {


    public static String beanToString(Object source){
        ObjectMapper mapper = new ObjectMapper();
        try {
           return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

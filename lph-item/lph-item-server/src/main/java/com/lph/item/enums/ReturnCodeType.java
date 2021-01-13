package com.lph.item.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * S:服务端异常
 * C:客户端异常(参数异常等)
 * F:三方异常
 */
@Getter
public enum ReturnCodeType {

    LOTTERY_TYPE_NOT_EXIST("C00001","彩票类型不存在"),

    
    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String name;

    ReturnCodeType(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getNameByCode(String code) {
        return Arrays.stream(ReturnCodeType.values())
                .filter(codeType -> codeType.getCode().equals(code))
                .findFirst()
                .map(ReturnCodeType::getName)
                .orElse("未知枚举项");
    }
}

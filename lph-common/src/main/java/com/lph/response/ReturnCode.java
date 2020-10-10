package com.lph.response;

import lombok.Getter;

import java.util.Arrays;

/**
 * S:服务端异常
 * C:客户端异常(参数异常等)
 * F:三方异常
 */
@Getter
public enum ReturnCode {

    SUCCESS("200","成功"),

    ERROR("500","服务端异常")

    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String name;

    ReturnCode(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getNameByCode(String code) {
        return Arrays.stream(ReturnCode.values())
                .filter(codeType -> codeType.getCode().equals(code))
                .findFirst()
                .map(ReturnCode::getName)
                .orElse("未知枚举项");
    }
}

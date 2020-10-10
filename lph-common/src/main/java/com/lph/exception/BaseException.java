package com.lph.exception;

import com.lph.response.ReturnCode;
import lombok.Data;


@Data
public class BaseException extends Exception{
    private static final long serialVersionUID = -3605030911925262261L;
    private String msg;
    private String code;

    public BaseException(String code) {
        super(code);
        initBaseException(code, ReturnCode.getNameByCode(code));
    }

    public BaseException(String code, String message) {
        super(code);
        initBaseException(code,message);
    }

    public BaseException(String code,String message, Throwable cause ) {
        super(code, cause);
        initBaseException(code,message);
    }

    public BaseException(String code,Throwable cause) {
        super(code,cause);
        this.code = code;
    }


    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        initBaseException(code,message);
    }

    public BaseException(ReturnCode returnCodeType){
        super(returnCodeType.getCode());
        initBaseException(returnCodeType.getCode(),returnCodeType.getName());
    }

    public BaseException(ReturnCode returnCodeType, Throwable cause){
        super(cause);
        initBaseException(returnCodeType.getCode(),returnCodeType.getName());
    }

    private void initBaseException(String code, String message){
        this.msg = message;
        this.code = code;
    }
}

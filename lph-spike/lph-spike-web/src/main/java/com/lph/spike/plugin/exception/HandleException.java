package com.lph.spike.plugin.exception;

import com.lph.common.exception.BaseException;
import com.lph.common.response.ReturnCode;
import com.lph.common.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * Created by @author lph
 */
@RestControllerAdvice
@Slf4j
public class HandleException {

    @ExceptionHandler(BaseException.class)
    public ServerResponse baseExceptionExecute(BaseException exception) {
        log.error(exception.getMsg(), exception);
        return ServerResponse.error(exception.getCode(), exception.getMsg());
    }


    @ExceptionHandler(Exception.class)
    public ServerResponse unknownExceptionExecute(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ServerResponse.error(exception);
    }

    private ServerResponse bindingResultException(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            return ServerResponse.error(ReturnCode.ERROR.getCode(), msg);
        }
        return ServerResponse.error();
    }
}

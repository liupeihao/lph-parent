package com.lph.item.plugin.aop;

import com.lph.common.util.convert.JacksonUtils;
import com.lph.common.util.http.HttpRequestIpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogAopAction {

    @Pointcut("execution(* com.lph.item.controller.*.*(..))")
    private void controllerAspect() {
    }

    /**
     * 方法开始执行
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint point) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Map<String,String> map=new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            map.put(name,request.getParameter(name));
        }
        log.info("IP: {}", HttpRequestIpUtil.getRemoteHost(request));
        log.info("url: {}", request.getRequestURI());
        log.info("参数: {}", JacksonUtils.beanToString(map));
    }


    /**
     * 方法结束执行后的操作
     */
    @AfterReturning(value = "controllerAspect()", returning = "returnObj")
    public void doAfter(JoinPoint point, Object returnObj) {
        String returnObjJson = JacksonUtils.beanToString(returnObj);
        log.info("反参：{}",  returnObjJson);
    }











}

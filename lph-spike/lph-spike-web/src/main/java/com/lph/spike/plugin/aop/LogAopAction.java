package com.lph.spike.plugin.aop;

import com.lph.common.util.http.HttpRequestIpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Configuration
public class LogAopAction {
    private static final String IP = "ip";
    private static final String URI = "uri";


    @Pointcut("execution(* com.lph.spike.controller.*.*(..))")
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
        log.info("请求IP: {}", HttpRequestIpUtil.getRemoteHost(request));
        log.info("请求路径: {}", request.getRequestURI());
        log.info("请求参数: {}", JacksonUtils.beanToString(map));
    }


    /**
     * 方法结束执行后的操作
     */
    @AfterReturning(value = "controllerAspect()", returning = "returnObj")
    public void doAfter(JoinPoint point, Object returnObj) {
        String returnObjJson = JacksonUtils.beanToString(returnObj);
        log.info("执行耗时时间:{}ms, 返回的参数信息：{}",  returnObjJson);
    }











}

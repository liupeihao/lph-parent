package com.lph.plugin.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author LPH
 * @Title: CrossInterceptor
 * @ProjectName zyzh-zz-idaccess
 * @date 2018/11/13 001317:58
 */

/**
 *  跨域拦截器
 */
public class CrossInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CrossInterceptor.class);

    @Value("${lph.config.allowUrls}")
    private String allowUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //这里只能设置一个url  或者全部  。
        response.addHeader("Access-Control-Allow-Origin","*");
        //请求方法
        response.addHeader("Access-Control-Allow-Methods","*");

        response.addHeader("Access-Control-Allow-Credentials","true");
        //允许token
        response.addHeader("Access-Control-Allow-Headers","Content-Type,token");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

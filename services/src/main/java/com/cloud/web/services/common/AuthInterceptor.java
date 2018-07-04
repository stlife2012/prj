package com.cloud.web.services.common;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
//        System.out.println(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        String token = request.getParameter("token");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println(">>>AuthInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
//        response.sendRedirect(request.getContextPath()+"/user/hi");
//        System.out.println("response code:" + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println(">>>AuthInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}

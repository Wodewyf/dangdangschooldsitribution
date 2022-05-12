package com.Filiter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class Logincheckfilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        1.获取本次请求的uri
        String requestURI = request.getRequestURI();
        String []urls = new String[]{
                "/admins/login/**/**",
                "/users/login/**/**",
                "/drivers/login/**/**",
                "/pages/login.html",
                "/pages/driverindex.html",
                "/pages/register.html",
                "/pages/driver/driverreceivetask.html",
                "/users",
                "/drivers",
                "/users/**",
                "/drivers/**"
        };
//        2.判断本次请求是否需要处理
        boolean getgo = getgo(urls, requestURI);

//        3.不需要处理，直接放行
        if(getgo){
            filterChain.doFilter(request,response);
//            结束执行
            return;
        }

//        4.判断登录状态
        if(request.getSession().getAttribute("admin") !=null
        ||request.getSession().getAttribute("user") !=null
                ||request.getSession().getAttribute("driver") !=null){
            filterChain.doFilter(request,response);
            return;
        }

//        {}是占位符 ，是对应
        log.info("拦截到请求:{}",request.getRequestURI());



    }

//    判断是否放行getgo方法
    boolean getgo(String[] urls,String uri){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, uri);
            if(match){
                return true;
            }
        }
        return false;
    }
}

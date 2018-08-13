package com.cf.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexFilter implements Filter{
    /**
     * 集合里面是不需要拦截的请求
     */

    List<String> urlList = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         //里面解析配置文件里面不需要拦截的请求.初始化到list里面
        System.out.println("------------------------------------------filterInit"+urlList);
        urlList.add("/helloWorld");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("------------------------------------------filter"+urlList.size());
            filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

package com.guat.eoas.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 获取请求的 URI
        String requestURI = httpRequest.getRequestURI();

        // 检查用户是否已经登录
        HttpSession session = httpRequest.getSession(false); // 获取现有的会话，不创建新会话

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // 判断是否需要登录
        // 1. 排除登录页面本身
        // 2. 需要登录的页面
        if (!isLoggedIn && !requestURI.endsWith("/login.jsp") && !requestURI.endsWith("/register.jsp") && !requestURI.endsWith("/login") && !requestURI.endsWith("/register")) {
            // 如果用户没有登录，跳转到登录页面
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?error=2");
            return; // 结束后续的过滤链处理
        }

        // 如果已经登录，则继续执行请求
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

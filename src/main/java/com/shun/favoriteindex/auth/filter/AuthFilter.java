package com.shun.favoriteindex.auth.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器 对没有进行登陆的用户进行过滤
 */
public class AuthFilter implements Filter {

    //不需要过滤的资源
    public static String EXCLUSIONS = "/,index.html,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/user/sendVerificationCode,/user/register,user/login";;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object user = ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (user == null) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}

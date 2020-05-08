package com.shun.favoriteindex.auth.filter;

import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import com.shun.favoriteindex.context.FiContextHolder;
import com.shun.favoriteindex.user.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 过滤器 对没有进行登陆的用户进行过滤
 */
public class AuthFilter implements Filter {

    //不需要过滤的资源
    public static String EXCLUSIONS = "/,/index.html,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/user/sendVerificationCode,/user/register,/user/login";;

    private Set<String> excludesPattern;

    private PatternMatcher pathMatcher = new ServletPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludesPattern = new HashSet<String>(Arrays.asList(EXCLUSIONS.split("\\s*,\\s*")));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            //todo 未登录应该有一个特殊的状态码
            if (servletResponse instanceof HttpServletResponse) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{'msg' : '未登录'}");
            }
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //请求资源路径
        String uri = request.getRequestURI();
        if (isExclusion(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //是否已经登录
        Object user = ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (user == null || !User.class.isInstance(user)) {
            //todo 未登录应该有一个特殊的状态码
            if (servletResponse instanceof HttpServletResponse) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{'msg' : '未登录'}");
            }
            return;
        }
        FiContextHolder.setUser((User) user);
        FiContextHolder.setSessionId(request.getSession().getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        excludesPattern = null;
    }

    /**
     * 是否不需要过滤的资源路径
     * @param requestURI
     * @return
     */
    private boolean isExclusion(String requestURI) {
        if (excludesPattern == null || requestURI == null) {
            return false;
        }

        for (String pattern : excludesPattern) {
            if (pathMatcher.matches(pattern, requestURI)) {
                return true;
            }
        }

        return false;
    }


}

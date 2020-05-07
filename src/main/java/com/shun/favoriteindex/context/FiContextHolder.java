package com.shun.favoriteindex.context;

import com.shun.favoriteindex.user.entity.User;

/**
 * 上下文
 */
public class FiContextHolder {

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    private static ThreadLocal<String> sessionIdHolder = new ThreadLocal<>();

    /**
     * 设置用户
     * @param user
     */
    public static void setUser(User user) {
        FiContextHolder.userHolder.set(user);
    }

    /**
     * 设置 sessionId
     * @param sessionId
     */
    public static void setSessionId(String sessionId) {
        FiContextHolder.sessionIdHolder.set(sessionId);
    }

    /**
     * 获取用户
     * @return
     */
    public static User getCurrUser() {
        return FiContextHolder.userHolder.get();
    }

    /**
     * 获取 sessionId
     * @return
     */
    public static String getSessionId() {
        return FiContextHolder.sessionIdHolder.get();
    }

}

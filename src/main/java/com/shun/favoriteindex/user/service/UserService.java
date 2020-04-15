package com.shun.favoriteindex.user.service;

import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.user.entity.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 发送验证码
     * @param recv 验证码收取邮箱
     * @return
     */
    FiResponse sendVerificationCode(String recv);

    /**
     * 用户注册
     * @param user
     * @return
     */
    FiResponse register(User user);
}

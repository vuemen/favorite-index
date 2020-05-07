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
     * @param verificationCode 验证码
     * @return
     */
    FiResponse register(User user, String verificationCode);

    /**
     * 登陆
     * @param email
     * @param password
     * @return
     */
    FiResponse login(String email, String password);

    /**
     * 获取用户完整信息
     * @param email
     * @return
     */
    User getUserFullInfo(String email);

    /**
     * 修改用户
     * @param user
     * @return
     */
    User modifyUser(User user);
}

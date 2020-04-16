package com.shun.favoriteindex.user.notify;

import com.shun.favoriteindex.user.entity.User;

/**
 * 用户注册通知接口
 */
public interface IUserRegisterNotify {

    /**
     * 用户注册成功通知
     * @param user
     */
    void successNotify(User user);
}

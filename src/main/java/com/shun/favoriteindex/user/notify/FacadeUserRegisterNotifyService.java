package com.shun.favoriteindex.user.notify;

import com.shun.favoriteindex.user.entity.User;

/**
 * 用户注册通知服务
 */
public interface FacadeUserRegisterNotifyService {

    /**
     * 用户注册成功通知
     * @param user
     */
    void successNotify(User user);
}

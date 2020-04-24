package com.shun.favoriteindex.user.notify.registerinfo;

import com.shun.favoriteindex.user.entity.RegisterUser;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.notify.IUserRegisterNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户注册通知   注册信息表数据处理
 */
@Component
public class UserRegisterNotify4RegisterInfo implements IUserRegisterNotify {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Map<String, RegisterUser> registeringUsers;

    @Override
    public void successNotify(User user) {
        registeringUsers.remove(user.getEmail());
    }

}

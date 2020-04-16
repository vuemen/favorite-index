package com.shun.favoriteindex.user.notify.registerinfo;

import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.mapper.UserMapper;
import com.shun.favoriteindex.user.notify.IUserRegisterNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户注册通知   注册信息表数据处理
 */
@Component
public class UserRegisterNotify4RegisterInfo implements IUserRegisterNotify {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public void successNotify(User user) {
        try {
            //用户注册成功后，删除用户注册信息表的相关数据
            userMapper.deleteRegisterUser(user.getEmail());
        } catch (Exception e) {
            logger.error("用户注册成功后，用户注册信息表数据删除失败。", e);
            e.printStackTrace();
        }
    }

}

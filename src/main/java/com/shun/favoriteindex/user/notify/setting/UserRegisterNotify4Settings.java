package com.shun.favoriteindex.user.notify.setting;

import com.shun.favoriteindex.setting.FacadeUserSettingService;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.notify.IUserRegisterNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认用户设置
 */
@Component
public class UserRegisterNotify4Settings implements IUserRegisterNotify {

    @Autowired
    private FacadeUserSettingService facadeUserSettingService;

    @Override
    public void successNotify(User user) {
        try {
            facadeUserSettingService.defaultSetting(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

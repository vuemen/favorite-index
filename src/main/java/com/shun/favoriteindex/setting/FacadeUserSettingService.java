package com.shun.favoriteindex.setting;

import com.shun.favoriteindex.user.entity.UserSettings;

/**
 * 用户设置对外服务
 */
public interface FacadeUserSettingService {

    /**
     * 获取用户设置
     * @param userId
     * @return
     */
    UserSettings getUserSettings(Long userId);

    /**
     * 默认设置
     */
    void defaultSetting(Long userId);

}

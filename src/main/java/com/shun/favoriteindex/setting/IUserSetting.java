package com.shun.favoriteindex.setting;

/**
 * 用户设置接口
 */
public interface IUserSetting {

    /**
     * 获取用户设置名称
     * @return
     */
    String getUserSettingName();

    /**
     * 获取用户设置value
     * @param userId
     * @return
     */
    Object getUserSettingValue(Long userId);

    /**
     * 默认设置
     */
    void defaultSetting(Long userId);
}

package com.shun.favoriteindex.setting.impl;

import com.shun.favoriteindex.setting.FacadeUserSettingService;
import com.shun.favoriteindex.setting.IUserSetting;
import com.shun.favoriteindex.user.entity.UserSettings;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacadeUserSettingServiceImpl implements FacadeUserSettingService, InitializingBean {

    @Autowired
    private List<IUserSetting> userSettingList;

    private Map<String, IUserSetting> userSettingMap;

    @Override
    public UserSettings getUserSettings(Long userId) {
        UserSettings userSettings = new UserSettings();

        userSettings.setUserId(userId);

        Map<String, Object> settings = new HashMap<>();

        userSettingMap.forEach((k, v) -> settings.put(k, v.getUserSettingValue(userId)));

        userSettings.setSettings(settings);

        return userSettings;
    }

    @Override
    public boolean modifySetting(String settingName, Object setting) {
        IUserSetting settingService = userSettingMap.get(settingName);
        if (setting == null) {
            return false;
        }
        settingService.modifyUserSetting(setting);
        return true;
    }

    @Override
    public void defaultSetting(Long userId) {
        userSettingMap.forEach((k, v) -> v.defaultSetting(userId));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userSettingMap == null) {
            userSettingMap = new HashMap<>();
        }
        for (IUserSetting setting : userSettingList) {
            userSettingMap.put(setting.getUserSettingName(), setting);
        }
    }
}

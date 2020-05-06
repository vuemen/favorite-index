package com.shun.favoriteindex.setting.impl;

import com.shun.favoriteindex.setting.FacadeUserSettingService;
import com.shun.favoriteindex.setting.IUserSetting;
import com.shun.favoriteindex.user.entity.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacadeUserSettingServiceImpl implements FacadeUserSettingService {

    @Autowired
    private List<IUserSetting> userSettingList;

    @Override
    public UserSettings getUserSettings(Long userId) {
        UserSettings userSettings = new UserSettings();userSettings.setUserId(userId);

        Map<String, Object> settings = new HashMap<>();

        for (IUserSetting setting : userSettingList) {
            settings.put(setting.getUserSettingName(), setting.getUserSettingValue(userId));
        }

        userSettings.setSettings(settings);

        return userSettings;
    }

    @Override
    public void defaultSetting(Long userId) {
        for (IUserSetting setting : userSettingList) {
            setting.defaultSetting(userId);
        }
    }

}

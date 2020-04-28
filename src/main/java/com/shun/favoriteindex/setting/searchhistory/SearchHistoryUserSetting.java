package com.shun.favoriteindex.setting.searchhistory;

import com.shun.favoriteindex.setting.IUserSetting;
import org.springframework.stereotype.Component;

/**
 * 用户设置 搜索历史
 */
@Component
public class SearchHistoryUserSetting implements IUserSetting {

    public static final String SETTING_NAME = "searchHistory";

    @Override
    public String getUserSettingName() {
        return SETTING_NAME;
    }

    @Override
    public Object getUserSettingValue(Long userId) {
        return null;
    }
}

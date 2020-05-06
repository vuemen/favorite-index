package com.shun.favoriteindex.setting.searchhistory;

import com.shun.favoriteindex.setting.IUserSetting;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户设置 搜索历史
 */
@Component
public class SearchHistoryUserSetting implements IUserSetting {

    public static final String SETTING_NAME = "searchHistory";

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 开关
     */
    private boolean hisSwitch;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isHisSwitch() {
        return hisSwitch;
    }

    public void setHisSwitch(boolean hisSwitch) {
        this.hisSwitch = hisSwitch;
    }

    @Override
    public String getUserSettingName() {
        return SETTING_NAME;
    }

    @Override
    public Object getUserSettingValue(Long userId) {
        return null;
    }

    @Override
    public void defaultSetting(Long userId) {

    }
}

package com.shun.favoriteindex.setting.searchhistory;

import com.shun.favoriteindex.setting.IUserSetting;
import com.shun.favoriteindex.setting.mapper.UserSettingMapper;
import com.shun.favoriteindex.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * 用户设置 搜索历史
 */
@Component
public class UserSetting4SearchHistory implements IUserSetting {

    @Autowired
    private UserSettingMapper userSettingMapper;

    public static final String SETTING_NAME = "searchHistory";

    @Override
    public String getUserSettingName() {
        return SETTING_NAME;
    }

    @Override
    public Object getUserSettingValue(Long userId) {
        return userSettingMapper.getSearchHistorySetting(userId);
    }

    @Override
    public void modifyUserSetting(Object setting) {
        SearchHistorySetting searchHistorySetting = (SearchHistorySetting) setting;
        String currTime = CommonUtil.dateFormat(new Date(), CommonUtil.YMDHMS_PATTERN);
        searchHistorySetting.setCreateTime(null);
        searchHistorySetting.setUpdateTime(currTime);
        userSettingMapper.updateSearchHistorySetting(searchHistorySetting);
    }

    @Override
    public void defaultSetting(Long userId) {
        SearchHistorySetting setting = new SearchHistorySetting();
        setting.setUserId(userId);
        setting.setHisSwitch(true);
        String currTime = CommonUtil.dateFormat(new Date(), CommonUtil.YMDHMS_PATTERN);
        setting.setCreateTime(currTime);
        setting.setUpdateTime(currTime);
        userSettingMapper.insertSearchHistorySetting(setting);
    }
}

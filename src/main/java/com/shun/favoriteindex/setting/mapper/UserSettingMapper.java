package com.shun.favoriteindex.setting.mapper;

import com.shun.favoriteindex.setting.searchhistory.SearchHistoryUserSetting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSettingMapper {

    SearchHistoryUserSetting getSearchHistorySetting(Long userId);
}

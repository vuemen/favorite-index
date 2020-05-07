package com.shun.favoriteindex.setting.mapper;

import com.shun.favoriteindex.setting.searchhistory.SearchHistorySetting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSettingMapper {

    SearchHistorySetting getSearchHistorySetting(Long userId);

    int insertSearchHistorySetting(SearchHistorySetting searchHistorySetting);

    int updateSearchHistorySetting(SearchHistorySetting searchHistorySetting);
}

package com.shun.favoriteindex.searchhistory.service.impl;

import com.shun.favoriteindex.context.FiContextHolder;
import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.searchhistory.entity.SearchHistory;
import com.shun.favoriteindex.searchhistory.mapper.SearchHistoryMapper;
import com.shun.favoriteindex.searchhistory.service.SearchHistoryService;
import com.shun.favoriteindex.setting.mapper.UserSettingMapper;
import com.shun.favoriteindex.setting.searchhistory.SearchHistorySetting;
import com.shun.favoriteindex.setting.searchhistory.UserSetting4SearchHistory;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.entity.UserSettings;
import com.shun.favoriteindex.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<SearchHistory> getAllSearchHistory(Map<String, Object> params) {
        return searchHistoryMapper.getAllSearchHistory(params);
    }

    @Override
    public FiResponse addSearchHistory(SearchHistory searchHistory) {
        //获取当前用户，查看是否开启记录历史的开关
        User user = FiContextHolder.getCurrUser();
        UserSettings settings = user.getSettings();
        SearchHistorySetting setting = (SearchHistorySetting) settings.getSettings().get(UserSetting4SearchHistory.SETTING_NAME);
        //开关未开启
        if (!setting.isHisSwitch()) {
            return FiResponse.getFailureResponse("搜索历史功能已关闭");
        }
        String currTime = CommonUtil.dateFormat(new Date(), CommonUtil.YMDHMS_PATTERN);
        searchHistory.setCreateTime(currTime);
        searchHistory.setUpdateTime(currTime);
        return FiResponse.getSuccessResponse(searchHistoryMapper.insertSearchHistory(searchHistory));
    }

    @Override
    public FiResponse delSearchHistoryByHisId(Long hisId) {
        return hisId == null ? FiResponse.getFailureResponse("删除失败") :
            FiResponse.getSuccessResponse(searchHistoryMapper.deleteSearchHistoryByHisId(hisId));
    }

    @Override
    public FiResponse delSearchHistoryByUserId(Long userId) {
        return userId == null ? FiResponse.getFailureResponse("删除失败") :
                FiResponse.getSuccessResponse(searchHistoryMapper.deleteSearchHistoryByUserId(userId));
    }
}

package com.shun.favoriteindex.searchhistory.service.impl;

import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.searchhistory.entity.SearchHistory;
import com.shun.favoriteindex.searchhistory.mapper.SearchHistoryMapper;
import com.shun.favoriteindex.searchhistory.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchHistoryImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<SearchHistory> getAllSearchHistory(Map<String, Object> params) {
        return searchHistoryMapper.getAllSearchHistory(params);
    }

    @Override
    public FiResponse addSearchHistory(SearchHistory searchHistory) {
        //获取当前用户，查看是否开启记录历史的开关

        return null;
    }

    @Override
    public FiResponse delSearchHistoryByHisId(Long hisId) {
        return hisId == null ? FiResponse.getFailureResponse("删除失败") :
            FiResponse.getSuccessResponse(searchHistoryMapper.deleteSearchHistoryByHisId(hisId));
    }

    @Override
    public FiResponse delSearchHistoryByUserId(Long userId) {
        return userId == null ? FiResponse.getFailureResponse("删除失败") :
                FiResponse.getSuccessResponse(searchHistoryMapper.deleteSearchHistoryByHisId(userId));
    }
}

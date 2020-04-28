package com.shun.favoriteindex.searchhistory.service;

import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.searchhistory.entity.SearchHistory;

import java.util.List;
import java.util.Map;

/**
 * 搜索历史服务
 */
public interface SearchHistoryService {

    /**
     * 根据条件获取搜索历史
     * @param params
     * @return
     */
    List<SearchHistory> getAllSearchHistory(Map<String, Object> params);

    /**
     * 添加搜索历史
     * @param searchHistory
     * @return
     */
    FiResponse addSearchHistory(SearchHistory searchHistory);

    /**
     * 删除指定的搜索历史
     * @return
     */
    FiResponse delSearchHistoryByHisId(Long hisId);

    /**
     * 删除指定用户所有的搜索历史
     * @return
     */
    FiResponse delSearchHistoryByUserId(Long userId);
}

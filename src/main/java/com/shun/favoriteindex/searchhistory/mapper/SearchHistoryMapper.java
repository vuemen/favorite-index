package com.shun.favoriteindex.searchhistory.mapper;

import com.shun.favoriteindex.searchhistory.entity.SearchHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchHistoryMapper {

    /**
     * 根据条件获取搜索历史
     * @param params
     * @return
     */
    List<SearchHistory> getAllSearchHistory(Map<String, Object> params);

    /**
     * 插入搜索历史
     * @param searchHistory
     * @return
     */
    int insertSearchHistory(SearchHistory searchHistory);

    /**
     * 更新搜索历史
     * @param searchHistory
     * @return
     */
    int updateSearchHistory(SearchHistory searchHistory);

    /**
     * 删除用户的搜索历史
     * @param userId
     * @return
     */
    int deleteSearchHistoryByUserId(Long userId);

    /**
     * 删除指定的搜搜历史
     * @param hisId
     * @return
     */
    int deleteSearchHistoryByHisId(Long hisId);
}

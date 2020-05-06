package com.shun.favoriteindex.searchhistory.controller;

import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.searchhistory.entity.SearchHistory;
import com.shun.favoriteindex.searchhistory.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/searchHistory", method = RequestMethod.POST)
public class SearchHistoryController {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @RequestMapping("/getAllSearchHistory")
    public FiResponse getAllSearchHistory(HttpRequest request) {
        try {
            //组装参数
            Map<String, Object> params = new HashMap<>();
            return FiResponse.getSuccessResponse(searchHistoryService.getAllSearchHistory(params));
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }

    @RequestMapping("addSearchHistory")
    public FiResponse addSearchHistory(SearchHistory searchHistory) {
        try {
            return FiResponse.getSuccessResponse(searchHistoryService.addSearchHistory(searchHistory));
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }

    @RequestMapping("delSearchHistoryByHisId")
    public FiResponse delSearchHistoryByHisId(Long hisId) {
        try {
            return FiResponse.getSuccessResponse(searchHistoryService.delSearchHistoryByHisId(hisId));
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }

    @RequestMapping("delSearchHistoryByUserId")
    public FiResponse delSearchHistoryByUserId(Long userId) {
        try {
            return FiResponse.getSuccessResponse(searchHistoryService.delSearchHistoryByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }

}

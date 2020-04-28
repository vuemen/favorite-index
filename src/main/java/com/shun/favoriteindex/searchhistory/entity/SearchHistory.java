package com.shun.favoriteindex.searchhistory.entity;

/**
 * 搜索历史
 */
public class SearchHistory {

    /**
     * id，数据库自增，主键
     */
    private Long hisId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 历史
     */
    private String history;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "userId=" + userId +
                ", history='" + history + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}

package com.shun.favoriteindex.setting.searchhistory;

/**
 * 搜索历史设置
 */
public class SearchHistorySetting {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 开关
     */
    private boolean hisSwitch;

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

    public boolean isHisSwitch() {
        return hisSwitch;
    }

    public void setHisSwitch(boolean hisSwitch) {
        this.hisSwitch = hisSwitch;
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
        return "SearchHistorySetting{" +
                "userId=" + userId +
                ", hisSwitch=" + hisSwitch +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}

package com.shun.favoriteindex.user.entity;

import java.util.Map;

/**
 * 用户设置
 */
public class UserSettings {

    /**
     * 用户id，数据库自增，主键
     */
    private Long userId;

    /**
     * 设置
     */
    private Map<String, Object> settings;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, Object> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, Object> settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        settings.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append(",");
        });
        sb.append("]");
        return "UserSettings{" +
                "userId=" + userId +
                ", settings=" + sb.toString() +
                '}';
    }
}

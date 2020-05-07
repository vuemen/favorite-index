package com.shun.favoriteindex.user.entity;

/**
 * 用户
 */
public class User {

    /**
     * 用户id，数据库自增，主键
     */
    private Long userId;

    /**
     * 邮箱，作为登陆账号
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像链接
     */
    private String headImg;

    /**
     * 搜索历史开关
     */
    private boolean hisSwitch;

    /**
     * 背景图是否跟随系统
     */
    private boolean bgiFlowSystem;

    /**
     * 背景图链接 背景图是否跟随系统为否时使用
     */
    private String bgiLink;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public boolean isHisSwitch() {
        return hisSwitch;
    }

    public void setHisSwitch(boolean hisSwitch) {
        this.hisSwitch = hisSwitch;
    }

    public boolean isBgiFlowSystem() {
        return bgiFlowSystem;
    }

    public void setBgiFlowSystem(boolean bgiFlowSystem) {
        this.bgiFlowSystem = bgiFlowSystem;
    }

    public String getBgiLink() {
        return bgiLink;
    }

    public void setBgiLink(String bgiLink) {
        this.bgiLink = bgiLink;
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
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", headImg='" + headImg + '\'' +
                ", hisSwitch=" + hisSwitch +
                ", bgiFlowSystem=" + bgiFlowSystem +
                ", bgiLink='" + bgiLink + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}

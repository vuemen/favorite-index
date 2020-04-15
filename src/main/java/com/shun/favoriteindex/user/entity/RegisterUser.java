package com.shun.favoriteindex.user.entity;

/**
 * 注册用户信息
 */
public class RegisterUser {

    /**
     * 验证码有效持续时间 5分钟
     */
    public static final Long EFFECT_DURATION_TIME = 1000L * 60 * 5;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 有效开始时间
     */
    private Long effectStartTime;

    /**
     * 有效结束时间
     */
    private Long effectEndTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Long getEffectStartTime() {
        return effectStartTime;
    }

    public void setEffectStartTime(Long effectStartTime) {
        this.effectStartTime = effectStartTime;
    }

    public Long getEffectEndTime() {
        return effectEndTime;
    }

    public void setEffectEndTime(Long effectEndTime) {
        this.effectEndTime = effectEndTime;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", effectStartTime='" + effectStartTime + '\'' +
                ", effectEndTime='" + effectEndTime + '\'' +
                '}';
    }
}

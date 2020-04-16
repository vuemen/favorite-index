package com.shun.favoriteindex.user.service.impl;

import com.shun.favoriteindex.mail.IMailService;
import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.user.entity.RegisterUser;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.mapper.UserMapper;
import com.shun.favoriteindex.user.notify.FacadeUserRegisterNotifyService;
import com.shun.favoriteindex.user.service.UserService;
import com.shun.favoriteindex.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final String REGISTER_EMAIL_SUBJECT = "favorite-index注册验证码";

    private static final String REGISTER_EMAIL_CONTENT_TEMPLATE_FILEPATH =
            new StringBuilder(System.getProperty("WORKING_PATH")).append("/emailTemplate/verificationCodeTemplate.html").toString();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IMailService mailService;

    @Autowired
    private FacadeUserRegisterNotifyService notifyService;

    @Value("${fi.user.defaultHeadImg}")
    private String defaultHeadImg;

    @Override
    public FiResponse sendVerificationCode(String recv) {
        //判断用户是否已经注册
        User user = userMapper.getUserByEmail(recv);
        if (user != null) {
            return FiResponse.getFailureResponse(
                    MessageFormat.format("邮箱{0}已经注册，请尝试登陆或者更改注册邮箱", recv));
        }

        //生成验证码
        String verificationCode = CommonUtil.generateVerificationCode(4);

        try {
            //发送邮件
            String content = MessageFormat.format
                    (CommonUtil.readFileContent(REGISTER_EMAIL_CONTENT_TEMPLATE_FILEPATH, "UTF-8"),
                    verificationCode, new Date().toString());
            mailService.sendMail(REGISTER_EMAIL_SUBJECT, content, recv);
        } catch (Exception e) {
            return FiResponse.getFailureResponse("验证码发送失败：" + e.getMessage());
        }

        //验证码入库
        Long effectStartTime = System.currentTimeMillis();
        Long effectEndTime = effectStartTime + RegisterUser.EFFECT_DURATION_TIME;
        RegisterUser registerUser = new RegisterUser();
        registerUser.setEmail(recv);
        registerUser.setVerificationCode(verificationCode);
        registerUser.setEffectStartTime(effectStartTime);
        registerUser.setEffectEndTime(effectEndTime);
        if (userMapper.updateRegisterUser(registerUser) <= 0) {
            userMapper.insertRegisterUser(registerUser);
        }

        return FiResponse.getSuccessResponse("验证码发送成功");
    }

    @Override
    public FiResponse register(User user, String verificationCode) {
        //校验验证码
        String email = user.getEmail();
        if (!this.checkVerificationCode(email, verificationCode)) {
            return FiResponse.getFailureResponse("验证码校验失败，可能是验证码错误或验证码过期");
        }

        //用户信息入库
        user.setHeadImg(defaultHeadImg);
        String currTime = CommonUtil.dateFormat(new Date(), CommonUtil.YMDHMS_PATTERN);
        user.setCreateTime(currTime);
        user.setUpdateTime(currTime);
        userMapper.insertUser(user);

        //用户注册成功通知
        try {
            notifyService.successNotify(user);
        } catch (Exception e) {
            //出现错误，删除用户信息
            userMapper.deleteUser(email);
            e.printStackTrace();
            return FiResponse.getFailureResponse("注册失败：" + e.getMessage());
        }

        return FiResponse.getSuccessResponse("注册成功。");
    }

    /**
     * 验证码校验
     * @param email             邮箱
     * @param verificationCode  验证码
     * @return
     */
    private boolean checkVerificationCode(String email, String verificationCode) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(verificationCode)) {
            return false;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("verificationCode", verificationCode);
        params.put("currTime", System.currentTimeMillis());
        return userMapper.getRegisterUserInfoCountByMap(params) > 0;
    }

}

package com.shun.favoriteindex.user.service.impl;

import com.shun.favoriteindex.mail.IMailService;
import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.user.entity.RegisterUser;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.mapper.UserMapper;
import com.shun.favoriteindex.user.notify.FacadeUserRegisterNotifyService;
import com.shun.favoriteindex.user.service.UserService;
import com.shun.favoriteindex.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Date;
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

    @Autowired
    private Map<String, RegisterUser> registeringUsers;

    @Override
    public FiResponse sendVerificationCode(String recv) {

        //判断用户是否已经注册
        User user = userMapper.getUserByEmail(recv);
        if (user != null) {
            return FiResponse.getFailureResponse
                    (MessageFormat.format("邮箱{0}已经注册，请尝试登陆或者更改注册邮箱", recv));
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
            return FiResponse.getFailureResponse("验证码发送失败");
        }

        //存储验证码
        Long effectStartTime = System.currentTimeMillis();
        Long effectEndTime = effectStartTime + RegisterUser.EFFECT_DURATION_TIME;
        RegisterUser registerUser = new RegisterUser();
        registerUser.setEmail(recv);
        registerUser.setVerificationCode(verificationCode);
        registerUser.setEffectStartTime(effectStartTime);
        registerUser.setEffectEndTime(effectEndTime);
        registeringUsers.put(recv, registerUser);

        return FiResponse.getSuccessResponse("验证码发送成功");
    }

    @Override
    public FiResponse register(User user, String verificationCode) {

        String email = user.getEmail();
        String message = null;

        //判断用户是否已经注册
        User existUser = userMapper.getUserByEmail(email);
        if (existUser != null) {
            return FiResponse.getFailureResponse
                    (MessageFormat.format("邮箱{0}已经注册，请尝试登陆或者更改注册邮箱", email));
        }

        //校验验证码
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
        if (StringUtils.isEmpty(verificationCode) ||
                !registeringUsers.containsKey(email) ||
                    registeringUsers.get(email) == null) {

            return false;
        } else {
            RegisterUser registerUser = registeringUsers.get(email);
            Long currTime = System.currentTimeMillis();
            //在有效期内正确的验证码通过校验
            if (verificationCode.equalsIgnoreCase(registerUser.getVerificationCode()) &&
                    currTime.compareTo(registerUser.getEffectStartTime()) >= 0 &&
                        currTime.compareTo(registerUser.getEffectEndTime()) <= 0) {
                return true;
            }
        }
        return false;
    }

}

package com.shun.favoriteindex.user.service.impl;

import com.shun.favoriteindex.mail.IMailService;
import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.user.entity.RegisterUser;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.mapper.UserMapper;
import com.shun.favoriteindex.user.service.UserService;
import com.shun.favoriteindex.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final String REGISTER_EMAIL_SUBJECT = "favorite-index注册验证码";

    private static final String REGISTER_EMAIL_CONTENT_TEMPLATE_FILEPATH =
            new StringBuilder(System.getProperty("WORKING_PATH")).append("/emailTemplate/verificationCodeTemplate.html").toString();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IMailService mailService;

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
            String content = MessageFormat.format(CommonUtil.readFileContent(REGISTER_EMAIL_CONTENT_TEMPLATE_FILEPATH),
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
    public FiResponse register(User user) {
        return null;
    }

}

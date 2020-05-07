package com.shun.favoriteindex.user.controller;

import com.shun.favoriteindex.context.FiContextHolder;
import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.user.entity.User;
import com.shun.favoriteindex.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/sendVerificationCode")
    public FiResponse sendVerificationCode(String email) {
        try {
            return userService.sendVerificationCode(email);
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }

    @RequestMapping("/register")
    public FiResponse register(User user, String verificationCode) {
        try {
            return userService.register(user, verificationCode);
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }

    @RequestMapping("/login")
    public FiResponse login(String email, String password, HttpServletRequest request) {
        try {
            userService.login(email, password);
            request.getSession().setAttribute("user", FiContextHolder.getCurrUser());
            FiContextHolder.setSessionId(request.getRequestedSessionId());
            return FiResponse.getSuccessResponse("登陆成功");
        } catch (Exception e) {
            e.printStackTrace();
            return FiResponse.getFailureResponse(e.getMessage());
        }
    }
}

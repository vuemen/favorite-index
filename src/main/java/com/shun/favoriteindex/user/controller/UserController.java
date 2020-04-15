package com.shun.favoriteindex.user.controller;

import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}

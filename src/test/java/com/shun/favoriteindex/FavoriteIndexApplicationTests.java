package com.shun.favoriteindex;

import com.shun.favoriteindex.mail.IMailService;
import com.shun.favoriteindex.response.FiResponse;
import com.shun.favoriteindex.util.FiEncrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FavoriteIndexApplicationTests {

    @Autowired
    IMailService mailService;

    @Test
    void contextLoads() {
    }

    @Test
    void encrypt() {
        System.out.println(FiEncrypt.encrypt("qwlnezqvicafbdac"));
    }

    @Test
    void decrypt() {
        System.out.println(FiEncrypt.decrypt("3P97uUVDU9g="));
    }

    @Test
    void response() {
        FiResponse response = FiResponse.getSuccessResponse("成功");
        System.out.println(response);
        FiResponse response1 = FiResponse.getSuccessResponse("ssss", "sss", "ddd");
        System.out.println(response1);
        FiResponse response3 = FiResponse.getFailureResponse("失败");
        System.out.println(response3);
        FiResponse response4 = FiResponse.getFailureResponse("ssss", "sss", "ddd");
        System.out.println(response4);
        FiResponse response5 = FiResponse.getFailureResponse("");
        System.out.println(response5);
    }

    @Test
    void sendEMail() {
        mailService.sendMail( "测试邮件主题", "测试邮件内容", "xxxxxxxx@xxxxx.com");
    }

}

package com.shun.favoriteindex.user.configuration;

import com.shun.favoriteindex.user.entity.RegisterUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户配置
 */
@Configuration
public class UserConfiguration {

    /**
     * 正在注册的用户
     * @return
     */
    @Bean
    public Map<String, RegisterUser> registeringUsers() {
        Map<String, RegisterUser> registeringUsers = new HashMap<String, RegisterUser>();
        return registeringUsers;
    }
}

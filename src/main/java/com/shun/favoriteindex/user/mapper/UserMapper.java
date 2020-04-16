package com.shun.favoriteindex.user.mapper;

import com.shun.favoriteindex.user.entity.RegisterUser;
import com.shun.favoriteindex.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 插入注册信息
     * @param registerUser
     * @return
     */
    int insertRegisterUser(RegisterUser registerUser);

    /**
     * 更新注册信息
     * @param registerUser
     * @return
     */
    int updateRegisterUser(RegisterUser registerUser);

    /**
     * 删除注册信息
     * @param email
     * @return
     */
    int deleteRegisterUser(String email);

    /**
     * 获取用户信息
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 按照条件查找满足条件的注册用户信息数量
     * @param params
     * @return
     */
    int getRegisterUserInfoCountByMap(Map<String, Object> params);

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户信息
     * @param email
     * @return
     */
    int deleteUser(String email);
}

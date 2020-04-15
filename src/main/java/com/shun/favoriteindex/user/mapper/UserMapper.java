package com.shun.favoriteindex.user.mapper;

import com.shun.favoriteindex.user.entity.RegisterUser;
import com.shun.favoriteindex.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
}

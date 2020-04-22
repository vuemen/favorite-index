package com.shun.favoriteindex.user.mapper;

import com.shun.favoriteindex.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 获取用户信息
     * @param email
     * @return
     */
    User getUserByEmail(String email);

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

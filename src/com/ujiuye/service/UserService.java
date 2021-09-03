package com.ujiuye.service;

import com.ujiuye.entity.User;

public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    boolean login(String username, String password);

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return 注册成功返回true，失败返回false
     */
    boolean register(String username, String password);

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 查询结果
     */
    boolean selectUserByUsername(String username);
}

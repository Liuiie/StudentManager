package com.ujiuye.service.impl;

import com.ujiuye.dao.UserDao;
import com.ujiuye.dao.impl.UserDaoImpl;
import com.ujiuye.entity.User;
import com.ujiuye.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @Override
    public boolean login(String username, String password){
        return userDao.login(username, password) != null;
    }

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return 注册成功返回true，失败返回false
     */
    @Override
    public boolean register(String username, String password){
        return userDao.register(username, password);
    }

    /**
     * 根据关键字查找用户
     * @param username 关键字
     * @return 查询结果
     */
    @Override
    public boolean selectUserByUsername(String username) {
        User user = userDao.selectUserByUsername(username);
        System.out.println(user);
        return user != null;
    }

}

package com.ujiuye.dao.impl;

import com.ujiuye.dao.UserDao;
import com.ujiuye.entity.User;
import com.ujiuye.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private QueryRunner qr = JdbcUtil.getQueryRunner();

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @Override
    public User login(String username, String password){

        User user = null;

        String sql = "select * from t_user where username=? and password=?";
        try {
            user = qr.query(sql, new BeanHandler<>(User.class),username,password);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return 注册成功返回true，失败返回false
     */
    @Override
    public boolean register(String username, String password){

        int result = 0;
        String sql = "insert into t_user(username,password) values(?,?)";
        try {
            result = qr.update(sql,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 1;
    }

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 查询结果
     */
    @Override
    public User selectUserByUsername(String username) {
        String sql = "select * from t_user where username=?";
        User user = null;
        try {
            user = qr.query(sql,new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}

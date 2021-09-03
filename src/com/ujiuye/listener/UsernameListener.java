package com.ujiuye.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebListener
public class UsernameListener implements ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {

    ServletContext sc = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 服务器启动时，创建存放登录的用户信息的容器
        Set<String> userSet = new HashSet<>();
        sc = sce.getServletContext();
        sc.setAttribute("userSet",userSet);
        System.out.println(userSet);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // 用户登录成功，在将用户信息放入session的同时，还要将用户信息放到之前创建的容器中
        Set<String> userSet = (Set<String>) sc.getAttribute("userSet");
        // 获取当前登录成功后放入session当中的用户名
        // se对象的getName用于获取当前放到session中的数据的key
        // se对象的getValue用于获取当前放到session中的数据的值
        // 判断传入session的是不是用户信息
        if ("username".equals(se.getName())){
            userSet.add((String)se.getValue());
        }
        // 将更新以后的容器重新放回ServletContext中
        sc.setAttribute("userSet",userSet);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 用户退出登录，销毁session的同时，将之前容器中该用户的信息移除掉
        // 获取容器
        Set<String> userSet = (Set<String>) sc.getAttribute("userSet");
        // 找到之前放进Session中的用户信息
        String username = (String)se.getSession().getAttribute("username");
        // 将容器中的该账户移除
        userSet.remove(username);
        // 将更新以后的容器重新放回ServletContext中
        sc.setAttribute("userSet",userSet);
    }
}

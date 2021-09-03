package com.ujiuye.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 设置请求对象编码集
        request.setCharacterEncoding("UTF-8");
        // 设置相应对象编码集
        response.setContentType("text/html;charset=UTF-8");
        // 获取操作标识
        String flag = request.getParameter("flag");
        // 利用反射获取字节码文件
        Class c = this.getClass();
        try {
            // 通过反射获取字节码文件中的方法
            Method m = c.getDeclaredMethod(flag,HttpServletRequest.class,HttpServletResponse.class);
            // 打破方法的修饰符限制
            m.setAccessible(true);
            // 调用操作标识对应的方法
            m.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

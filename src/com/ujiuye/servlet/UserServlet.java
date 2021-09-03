package com.ujiuye.servlet;

import com.ujiuye.service.UserService;
import com.ujiuye.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    // 创建用户操作对象
    UserService userService = new UserServiceImpl();

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取输入的用户和密码
        String username = request.getParameter("username");
        String password = request.getParameter("pwd");
        // 获取用户对象
        boolean result = userService.login(username,password);
        if (result) {

            // 创建用户名的Cookie对象
            Cookie cookieUsername = new Cookie("username",username);                         // 给用户名的Cookie对象设置保留时长
            cookieUsername.setMaxAge(60*60*24*365);
            // 将Cookie发送到客户端
            response.addCookie(cookieUsername);

            // 判断是否设置记住密码
            setPasswordCookie(request, response, password);

            // 获取Session对象
            HttpSession session = request.getSession();
            session.setAttribute("username",username);

            // 登录成功
            System.out.println(username + "登录成功！");
            response.sendRedirect("functionButton.jsp");
        } else {
            // 登录失败
            System.out.println(username + "登录失败！");
            response.sendRedirect("index.jsp");
        }
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取注册信息
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if (password1.equals(password2)){
            // 获取注册结果
            boolean result = userService.register(username,password1);
            if (result){
                // 注册成功
                response.sendRedirect("index.jsp");
                return;
            } else {
                // 注册失败
                response.sendRedirect("userRegister.html");
            }
        } else {
            // 两次密码不一致
            response.sendRedirect("userRegister.html");
        }
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取Session对象
        HttpSession session = request.getSession();
        // 销毁Session
        session.invalidate();
        // 跳转到登录界面
        response.sendRedirect("index.jsp");
    }

    /**
     * 设置是否记住密码
     * @param request 请求对象
     * @param response 相应对象
     * @param password 密码
     */
    private void setPasswordCookie(HttpServletRequest request, HttpServletResponse response, String password) {
        // 判读是否记住密码
        String rem = request.getParameter("cookie");
        if ("ok".equals(rem)){
            // 创建用密码，是否记住密码的Cookie对象
            Cookie cookiePassword = new Cookie("password",password);
            Cookie cookieRem = new Cookie("rem",rem);
            // 给密码，是否记住密码的Cookie对象设置保留时长
            cookiePassword.setMaxAge(60*60*24);
            cookieRem.setMaxAge(60*60*24);
            // 将Cookie发送到客户端
            response.addCookie(cookiePassword);
            response.addCookie(cookieRem);
        } else {
            // 创建密码，是否记住密码的Cookie对象,设置为空值，覆盖原Cookie对象
            Cookie cookiePassword = new Cookie("password",null);
            Cookie cookieRem = new Cookie("rem",null);
            // 给用户名，密码，是否记住密码的Cookie对象设置保留时长为0，消除记住的账号密码
            cookiePassword.setMaxAge(0);
            cookieRem.setMaxAge(0);
            // 将Cookie发送到客户端
            response.addCookie(cookiePassword);
            response.addCookie(cookieRem);
        }
    }

    protected void selectUserByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        boolean result = userService.selectUserByUsername(username);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.close();
    }
}

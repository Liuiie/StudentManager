<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录界面</title>
    <style type="text/css">
      form{
        width: 250px;
        height: 200px;
        border: 1px solid black;
        margin: 0px auto;
        text-align: center;
        border-radius: 10px;
      }
      a{
        text-decoration: none;
      }
      #cookie{
        text-align: left;
        margin: 6px 10px;
      }
    </style>
  </head>
  <body>
    <%
      Cookie[] cookies = request.getCookies();
      if (cookies != null){
        for (Cookie cookie : cookies){
          if ("username".equals(cookie.getName())){
            pageContext.setAttribute("username",cookie.getValue());
          }
          if ("password".equals(cookie.getName())){
            pageContext.setAttribute("password",cookie.getValue());
          }
          if ("rem".equals(cookie.getName())){
            pageContext.setAttribute("rem",cookie.getValue());
          }
        }
      }
    %>
    <form action="userServlet" method="post">
      <h2>登录</h2>
      <input type="hidden" name="flag" value="login">
      账号：<input type="text" name="username" value="${username}"><br><br>
      密码：<input type="password" name="pwd" value="${password}"><br>
      <div id="cookie">
        <input type="checkbox" name="cookie" value="ok" ${empty rem ? "" : "checked"}>记住密码
      </div>
      <input type="submit" value="登录">
      <a href="userRegister.html"> <input type="button" value="注册"></a>
    </form>
  </body>
</html>

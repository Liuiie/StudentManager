<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作选择</title>
    <style type="text/css">
        *{
            text-decoration: none;
        }
        input{
            margin: 3px;
            font-size: 20px;
            width: 140px;
            height: 32px;
        }
        form{
            width: 240px;
            border: 1px solid black;
            border-radius: 3%;
            background-color: beige;
            margin: 6px 16px;
        }
    </style>
</head>
<body>
    <%-- 未登录情况下不允许访问该界面 --%>
    <%--<%
        Object username = session.getAttribute("username");
        if (username == null){
            response.sendRedirect("index.jsp");
        }
    %>--%>
    <div align="center">
        <!--    学生信息展示    -->
        <a href="studentServlet?flag=getStudentByPage&page=1">
            <input type="button" value="学生信息展示">
        </a><br>
        <!--    新增学生    -->
        <a href="insertStudent.jsp">
            <input type="button" value="新增学生">
        </a><br>
        <!-- 搜索 -->
        <form action="studentServlet" method="get">
            <input type="hidden" name="flag" value="selectStudent">
            学号：
            <input type="text" name="sid"><br>
            <input type="submit" value="搜索" style="width: 60px;">
        </form>
        <!-- 搜索 -->
        <form action="studentServlet" method="get">
            <input type="hidden" name="flag" value="selectStudentByName">
            关键字：
            <input type="text" name="sname"><br>
            <input type="submit" value="搜索" style="width: 60px;">
        </form>
        <!--   退出   -->
        <a href="userServlet?flag=logout">
            <input type="button" value="退出">
        </a>
        <div>
            当前在线用户：
            <%
                Set<String> userSet = (Set<String>)application.getAttribute("userSet");
                for (String string : userSet){
                    out.print(string + " ");
                }
            %>
        </div>
    </div>
</body>
</html>

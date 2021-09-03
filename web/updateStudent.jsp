<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>修改学生信息</title>
    <style type="text/css">
        form{
            width: 300px;
            height: 300px;
            border: 1px solid black;
            text-align: center;
            margin: 0px auto;
            border-radius: 3%;
            background-color: blanchedalmond;
        }
    </style>
</head>
<body>
    <%-- 未登录情况下不允许访问该界面 --%>
    <%--<%
        Object username = session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("index.jsp");
        }
    %>--%>
    <form action="studentServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="flag" value="updateStudent2">
        <input type="hidden" name="sid" value="${student.sid}">
        <h2 align="center">学生信息修改</h2>
        <table width="250px" align="center">
            <tr>
                <td>姓名：</td>
                <td>
                    <input type="text" name="sname" value="${student.sname}">
                </td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td>
                    <input type="text" name="sage" value="${student.sage}">
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td style="text-align: left;">
                    <input type="radio" name="sgender" value="男" ${"男".equals(student.sgender) ? "checked" : ""}>男
                    <input type="radio" name="sgender" value="女" ${"女".equals(student.sgender) ? "checked" : ""}>女
                </td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td>
                    <input type="text" name="semail"  value="${student.semail}">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <img src="http://localhost:8080/student_pic/${student.photo}" width="50" style="margin: 0px 90px;">
                </td>
            </tr>
            <tr>
                <td>头像：</td>
                <td>
                    <input type="file" name="newphoto" style="width: 180px;">
                    <input type="hidden" name="oldphoto" value="${student.photo}">
                </td>
            </tr>
        </table>
        <input type="submit" value="提交"  style="margin: 6px 0px">
        <!--    返回按钮    -->
        <a href="studentServlet?flag=getStudentByPage&page=1&studentId=${student.sid}">
            <input type="button" value="返回">
        </a>
    </form>
</body>
</html>

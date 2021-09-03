<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息新增</title>
    <style type="text/css">
        form{
            width: 350px;
            height: 250px;
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
        <input type="hidden" name="flag" value="insert">
        <h2 align="center">学生信息新增</h2>
        <table align="center" style="width: 260px;">
            <tr>
                <td id="firstColumn">姓名：</td>
                <td>
                    <input type="text" name="sname">
                </td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td>
                    <input type="text" name="sage">
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td style="text-align: left;">
                    <input type="radio" name="sgender" value="男" checked>男
                    <input type="radio" name="sgender" value="女">女
                </td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td>
                    <input type="text" name="semail">
                </td>
            </tr>
            <tr>
                <td>头像：</td>
                <td>
                    <input type="file" name="photo" style="width: 180px;">
                </td>
            </tr>
        </table>
        <input type="submit" value="保存"  style="margin: 6px 0px">
        <!--    返回按钮    -->
        <a href="functionButton.jsp">
            <input type="button" value="返回">
        </a>
    </form>
</body>
</html>
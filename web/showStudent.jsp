<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>学生信息展示</title>
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        $(function(){
            // 给全选绑定点击事件
            $("#checkAll").click(function(){
                // 设置每个选项的checked的值与全选选项一致
                $("[name=studentId]").each(function(){
                    this.checked = $("#checkAll").prop("checked");
                });
            });
            // 给其他选项绑定点击事件，当其他选项被取消时，全选按钮取消,全部选中时，全选按钮选中
            $("[name=studentId]").click(function(){
                $("[name=studentId]").each(function(index,content){
                    console.log(content);
                    if(content.checked === false){
                        $("#checkAll").each(function(){
                            this.checked = false;
                        });
                        return false;
                    }
                    $("#checkAll").each(function(){
                        this.checked = true;
                    });
                });
            });

            //批量删除
            $("#batchDelete").click(function() {
                // 创建一个数组存放获取到的sid
                var sidsArr = new Array();
                // 获取所有被选中的checkone的value属性的值
                $("[name=studentId]:checked").each(function (index, content) {
                    var sid = $(content).val();
                    // push将数据放入数组中
                    sidsArr.push(sid);
                });
                if (sidsArr.length == 0) {
                    alert("你不选咋个删呐！")
                } else {
                    // 将数组转换成字符串，join()将数组中的数据转换成用逗号隔开的字符串
                    var stringIds = sidsArr.join();
                    window.location.href = "studentServlet?flag=batchDelete&studentIds=" + stringIds;
                }
            });
        });
    </script>
    <style type="text/css">
        #buttonsBox{
            margin: 6px auto 0px;
            width: 600px;
        }
        #pageButtons{
            display: inline-block;
            margin: 0px 136px;
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
    <!-- 搜索 -->
    <div align="center">
        <form action="studentServlet" method="get">
            <input type="hidden" name="flag" value="selectStudentByName">
            关键字：
            <input type="text" name="sname" value="${sname}">
            <input type="submit" value="搜索" style="width: 60px;">
            <a href="insertStudent.jsp">
                <input type="button" value="新增学生">
            </a>
        </form>
    </div>
    <%--  学生信息展示表头  --%>
    <table border="1" cellpadding="0" cellspacing="0" align="center" width="600px">
        <tr align="center">
            <td>
                <input type="checkbox" id="checkAll">
            </td>
            <td>学号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>性别</td>
            <td>邮箱</td>
            <td>头像</td>
            <td>修改学生信息</td>
            <td>删除学生</td>
        </tr>
        <%-- 所有学生信息展示 --%>
        <c:forEach var="student" items="${studentList}">
            <tr align="center">
                <td>
                    <input type="checkbox" name="studentId" value="${student.sid}">
                </td>
                <td>${student.sid}</td>
                <td>${student.sname}</td>
                <td>${student.sage}</td>
                <td>${student.sgender}</td>
                <td>${student.semail}</td>
                <td>
                    <img src="http://localhost:8080/student_pic/${student.photo}" width="50">
                </td>
                <td>
                    <a href="studentServlet?flag=updateStudent1&studentId=${student.sid}">修改信息</a>
                </td>
                <td>
                    <a href="studentServlet?flag=deleteStudentById&studentId=${student.sid}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--   批量删除   --%>
    <div id="buttonsBox">
        <input id="batchDelete" type="button" value="批量删除" align="left">
        <div id="pageButtons" align="center">
            <a href="studentServlet?flag=selectStudentByName&sname=${sname}&page=1" >首页</a>
            <a href="studentServlet?flag=selectStudentByName&sname=${sname}&page=${pageUtil.prevPage}" >上一页</a>
            ${pageUtil.page}/${pageUtil.countPage}
            <a href="studentServlet?flag=selectStudentByName&sname=${sname}&page=${pageUtil.nextPage}" >下一页</a>
            <a href="studentServlet?flag=selectStudentByName&sname=${sname}&page=${pageUtil.countPage}" >尾页</a>
        </div>
        <%-- 返回按钮 --%>
        <a href="functionButton.jsp">
            <input type="button" value="返回" align="right">
        </a>
    </div>
</body>
</html>


package com.ujiuye.servlet;

import com.ujiuye.dao.impl.StudentDaoImpl;
import com.ujiuye.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/studentTestServlet")
public class StudentTestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        StudentDaoImpl sd = new StudentDaoImpl();
        List<Student> list = sd.getAllStudent();
        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder();
        String header = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>学生信息展示</title>\n" +
                "    <style type=\"text/css\">\n" +
                "        div{\n" +
                "            width: 250px;\n" +
                "            height: 200px;\n" +
                "            border: 1px solid black;\n" +
                "            border-radius: 3%;\n" +
                "            background-color: pink;\n" +
                "            display: inline-block;\n" +
                "            margin: 6px;\n" +
                "        }\n" +
                "        h3{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        p{\n" +
                "            margin-left: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>";
        sb.append(header);
        for (Student s : list){

            System.out.println(s);
            String str = "<div>\n" +
                    "        <h3>学生信息</h3>\n" +
                    "        <p>姓名：" + s.getSname() + "</p>\n" +
                    "        <p>年龄：" + s.getSage() + "</p>\n" +
                    "        <p>性别：" + s.getSgender() + "</p>\n" +
                    "        <p>邮箱：" + s.getSemail() + "</p>\n" +
                    "    </div>";

            sb.append(str);
        }
        String end = "</body>\n" +
                "</html>";
        sb.append(end);
        out.print(sb.toString());
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

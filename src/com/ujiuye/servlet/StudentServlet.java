package com.ujiuye.servlet;

import com.ujiuye.entity.Student;
import com.ujiuye.service.StudentService;
import com.ujiuye.service.impl.StudentServiceImpl;
import com.ujiuye.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/studentServlet")
public class StudentServlet extends BaseServlet {

    // 创建学生操作对象
    StudentService studentService = new StudentServiceImpl();

    /**
     * 新增学生
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取输入的学生信息
        String sname = request.getParameter("sname");
        int sage = Integer.parseInt(request.getParameter("sage"));
        String sgender = request.getParameter("sgender");
        String semail = request.getParameter("semail");

        // 文件对象处理
        Part p = request.getPart("photo");
        String photo = UUID.randomUUID() + p.getSubmittedFileName();
        String path = "E:\\Java\\IDEA\\PhaseII\\StudentManager\\student_pic";
        File studentPicName = new File(path);
        if (!studentPicName.exists()){
            studentPicName.mkdirs();
        }
        p.write(path + "\\" + photo);

        // 新增学生到数据库
        boolean result = studentService.insertStudent(new Student(sname,sage,sgender,semail,photo));
        if (result){
            // 返回学生展示页面
            response.sendRedirect("studentServlet?flag=getStudentByPage");
        } else {
            // 返回学生新增界面
            response.sendRedirect("insertStudent.jsp");
        }
    }

    /**
     * 根据学号获取学生信息并跳转到updateStudent.jsp展示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateStudent1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取学生学号
        int sid = Integer.parseInt(request.getParameter("studentId"));
        // 获取学生信息
        Student student = studentService.selectStudentById(sid);
        // 将学生对象放到一块内存中
        request.setAttribute("student",student);
        // 跳转到updateStudent.jsp
        request.getRequestDispatcher("/updateStudent.jsp").forward(request,response);
    }

    /**
     * 获取updateStudent.jsp界面学生信息并修改该，最后跳转到showStudent.jsp展示修改后数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateStudent2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取学生信息
        int sid = Integer.parseInt(request.getParameter("sid"));
        String sname = request.getParameter("sname");
        int sage = Integer.parseInt(request.getParameter("sage"));
        String sgender = request.getParameter("sgender");
        String semail = request.getParameter("semail");

        String photo = "";
        // 头像文件处理
        Part partPhoto = request.getPart("newphoto");
        String fileName = partPhoto.getSubmittedFileName();
        if (!("".equals(fileName))){
            String path = "E:\\Java\\IDEA\\PhaseII\\StudentManager\\student_pic";
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            photo = UUID.randomUUID() + fileName;
            partPhoto.write(path + "\\" +photo);
        } else {
            photo = request.getParameter("oldphoto");
        }
        // 修改学生信息
        boolean result = studentService.updateStudent(new Student(sid,sname,sage,sgender,semail,photo));
        if (result){
            // 返回学生展示页面
            response.sendRedirect("studentServlet?flag=getStudentByPage");
        } else {
            response.sendRedirect("/updateStudent.jsp");
        }
    }

    /**
     * 根据学号批量删除学生并跳转到showStudent.jsp展示其余数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    /*protected void batchDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取批量删除学生编号
        String[] studentIds = request.getParameterValues("studentId");
        // 删除学生
        for (String studentId : studentIds){
            int id = Integer.parseInt(studentId);
            studentService.deleteStudent(id);
        }
        // 返回学生展示页面
        response.sendRedirect("studentServlet?flag=getStudentByPage");
    }*/

    /**
     * 根据学号批量删除学生并跳转到showStudent.jsp展示其余数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void batchDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取批量删除学生编号
        String stringSid = request.getParameter("studentIds");
        // 删除学生
        boolean result = studentService.batchDelete(stringSid);
        // 返回学生展示页面
        response.sendRedirect("studentServlet?flag=getStudentByPage");
    }

    /**
     * 根据学号删除学生并跳转到showStudent.jsp展示其余数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteStudentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 获取要删除学生的学号
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        // 执行删除操作
        studentService.deleteStudentById(studentId);
        // 返回学生展示页面
        response.sendRedirect("studentServlet?flag=getStudentByPage");
    }

    /**
     * 根据学号查找学生并跳转到showStudent.jsp展示数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void selectStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取查询学生学号
        String result = request.getParameter("sid");
        if (!("".equals(result))){
            int sid = Integer.parseInt(result);
            // 获取学生信息并跳转到student.jsp展示数据
            List<Student> list = new ArrayList<>();
            Student student = studentService.selectStudentById(sid);
            if (student != null){
                list.add(student);
                // 将所有学生对象集合放到一块内存中
                request.setAttribute("studentList", list);
                // 跳转到student.jsp展示数据
                // Dispatcher:分发   forward:转发
                request.getRequestDispatcher("/showStudent.jsp").forward(request, response);
            } else {
                response.sendRedirect("functionButton.jsp");
            }
        } else {
            getStudentByPage(request,response);
        }
    }

    /**
     * 根据名字查找学生并跳转到showStudent.jsp展示数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void selectStudentByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取页码
        String page = request.getParameter("page");
        // 获取搜索名字
        String sname = request.getParameter("sname");
        // 设置每页显示条数
        int rows = 5;
        // 获取数据总条数
        int countRows = studentService.getNameCountRows(sname);
        // 初始化分页工具
        PageUtil pageUtil = new PageUtil(page,rows,countRows);
        // 获取对应页数学生对象集合
        List<Student> list = studentService.selectStudentByName(sname,pageUtil);
        // 将所有学生对象集合放到一块内存中
        request.setAttribute("studentList", list);
        request.setAttribute("pageUtil",pageUtil);
        request.setAttribute("sname",sname);
        // 跳转到student.jsp展示数据
        // Dispatcher:分发   forward:转发
        request.getRequestDispatcher("/showStudent.jsp").forward(request, response);
    }

    /**
     * 获取所有学生信息并跳转到showStudent.jsp展示数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取所有学生对象集合
        List<Student> list = studentService.getAllStudent();
        // 将所有学生对象集合放到一块内存中
        request.setAttribute("studentList", list);
        // 跳转到student.jsp展示数据
        // Dispatcher:分发   forward:转发
        request.getRequestDispatcher("/showStudent.jsp").forward(request, response);
    }

    /**
     * 获取对应页数学生信息并跳转到showStudent.jsp展示数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void getStudentByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取页码
        String page = request.getParameter("page");
        // 设置每页显示条数
        int rows = 5;
        // 获取数据总条数
        int countRows = studentService.getCountRows();
        // 初始化分页工具
        PageUtil pageUtil = new PageUtil(page,rows,countRows);
        // 获取对应页数学生对象集合
        List<Student> list = studentService.getStudentByPage(pageUtil);
        // 将所有学生对象集合放到一块内存中
        request.setAttribute("studentList", list);
        request.setAttribute("pageUtil",pageUtil);
        // 跳转到student.jsp展示数据
        // Dispatcher:分发   forward:转发
        request.getRequestDispatcher("/showStudent.jsp").forward(request, response);
    }
}

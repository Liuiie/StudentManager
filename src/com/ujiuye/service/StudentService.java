package com.ujiuye.service;

import com.ujiuye.entity.Student;
import com.ujiuye.util.PageUtil;

import java.util.List;

public interface StudentService {

    /**
     * 展示所有学生信息
     * @return 返回所有学生对象集合
     */
    List<Student> getAllStudent();

    /**
     * 新增学生
     * @param student 学生
     * @return 成功返回true，失败返回false
     */
    boolean insertStudent(Student student);

    /**
     * 修改学生信息
     * @param student 学生
     * @return 成功返回true，失败返回false
     */
    boolean updateStudent(Student student);

    /**
     * 获取指定信息学生信息条数
     * @return 指定信息学生信息条数
     */
    int getNameCountRows(String sname);

    /**
     * 根据名字查找学生
     * @param sname 学生学号
     * @return 当学生存在时返回学生对象，不存在时返回null
     */
    List<Student> selectStudentByName(String sname,PageUtil pageUtil);

    /**
     * 根据学号查找学生
     * @param sid 学生学号
     * @return 当学生存在时返回学生对象，不存在时返回null
     */
    Student selectStudentById(int sid);

    /**
     * 删除学生信息
     * @param sid 学号
     * @return 成功返回true，失败返回false
     */
    boolean deleteStudentById(int sid);

    /**
     * 根据分页工具查出对应学生数据
     * @param pageUtil 分页工具对象
     * @return 返回学生信息集合
     */
    List<Student> getStudentByPage(PageUtil pageUtil);

    /**
     * 获取学生信息总条数
     * @return 学生信息总条数
     */
    int getCountRows();

    /**
     * 批量删除学生
     * @param stringSid 批量删除学生学号字符串形式
     */
    boolean batchDelete(String stringSid);
}

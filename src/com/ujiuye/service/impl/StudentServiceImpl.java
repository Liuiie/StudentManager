package com.ujiuye.service.impl;

import com.ujiuye.dao.StudentDao;
import com.ujiuye.dao.impl.StudentDaoImpl;
import com.ujiuye.entity.Student;
import com.ujiuye.service.StudentService;
import com.ujiuye.util.PageUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDao sd = new StudentDaoImpl();

    /**
     * 展示所有学生信息
     * @return 返回所有学生对象集合
     */
    public List<Student> getAllStudent(){
        return sd.getAllStudent();
    }

    /**
     * 新增学生
     * @param student 学生
     * @return 成功返回true，失败返回false
     */
    public boolean insertStudent(Student student){
        return sd.insertStudent(student);
    }

    /**
     * 修改学生信息
     * @param student 学生
     * @return 成功返回true，失败返回false
     */
    public boolean updateStudent(Student student){
        return sd.updateStudent(student);
    }

    /**
     * 获取指定信息学生信息条数
     * @return 指定信息学生信息条数
     */
    @Override
    public int getNameCountRows(String sname) {
        return sd.getNameCountRows(sname);
    }

    /**
     * 根据名字查找学生
     * @param sname 学生学号
     * @return 当学生存在时返回学生对象，不存在时返回null
     */
    @Override
    public List<Student> selectStudentByName(String sname,PageUtil pageUtil) {
        return sd.selectStudentByName(sname,pageUtil);
    }

    /**
     * 根据学号查找学生
     * @param sid 学生学号
     * @return 当学生存在时返回学生对象，不存在时返回null
     */
    public Student selectStudentById(int sid){
        return sd.selectStudentById(sid);
    }

    /**
     * 删除学生信息
     * @param sid 学号
     * @return 成功返回true，失败返回false
     */
    public boolean deleteStudentById(int sid){
        return sd.deleteStudentById(sid);
    }

    /**
     * 根据分页工具查出对应学生数据
     * @param pageUtil 分页工具对象
     * @return 返回学生信息集合
     */
    @Override
    public List<Student> getStudentByPage(PageUtil pageUtil) {
        return sd.getStudentByPage(pageUtil);
    }

    /**
     * 获取学生信息总条数
     * @return 学生信息总条数
     */
    @Override
    public int getCountRows() {
        return sd.getCountRows();
    }

    /**
     * 批量删除学生
     * @param stringSid 批量删除学生学号字符串形式
     */
    @Override
    public boolean batchDelete(String stringSid) {
        return sd.batchDelete(stringSid);
    }
}

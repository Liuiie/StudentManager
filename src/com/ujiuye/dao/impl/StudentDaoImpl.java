package com.ujiuye.dao.impl;

import com.ujiuye.dao.StudentDao;
import com.ujiuye.entity.Student;
import com.ujiuye.util.JdbcUtil;
import com.ujiuye.util.PageUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private QueryRunner qr = JdbcUtil.getQueryRunner();

    /**
     * 展示所有学生信息
     * @return 返回所有学生对象集合
     */
    @Override
    public List<Student> getAllStudent(){

        String sql = "select * from t_student";
        List<Student> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 新增学生信息
     * @param student 学生
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean insertStudent(Student student){

        String sql = "insert into t_student(sname,sage,sgender,semail,photo) values(?,?,?,?,?)";
        int rows = 0;
        try {
            rows = qr.update(sql,student.getSname(),student.getSage(),student.getSgender(),student.getSemail(),student.getPhoto());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows == 1;
    }

    /**
     * 删除学生信息
     * @param sid 学号
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean deleteStudentById(int sid){

        String sql = "delete from t_student where sid=?";
        int rows = 0;
        try {
            rows  = qr.update(sql,sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows == 1;
    }

    /**
     * 根据学号查找学生
     * @param sid 学生学号
     * @return 当学生存在时返回学生对象，不存在时返回null
     */
    @Override
    public Student selectStudentById(int sid){

        Student student = null;
        String sql = "select * from t_student where sid=?";
        try {
            student = qr.query(sql,new BeanHandler<>(Student.class),sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * 获取获取指定信息学生信息条数
     * @return 获取指定信息学生信息条数
     */
    @Override
    public int getNameCountRows(String sname) {

        String sql = "select count(*) from t_student where sname like '%" + sname + "%'";
        int countRows = 0;
        try {
            countRows = (int)(long)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    /**
     * 根据名字查找学生
     * @param sname 学生学号
     * @return 当学生存在时返回学生对象，不存在时返回null
     */
    @Override
    public List<Student> selectStudentByName(String sname,PageUtil pageUtil) {
        String sql = "select * from t_student where sname like '%" + sname + "%' limit ?,?";
        List<Student> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Student.class),pageUtil.getIndex(),pageUtil.getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 修改学生信息
     * @param student 学生
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean updateStudent(Student student){

        String sql = "update t_student set sname=?,sage=?,sgender=?,semail=?,photo=? where sid=?";
        int rows = 0;
        try {
            rows = qr.update(sql,student.getSname(),student.getSage(),student.getSgender(),student.getSemail(),student.getPhoto(),student.getSid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows == 1;
    }

    /**
     * 根据分页工具查出对应学生数据
     * @param pageUtil 分页工具对象
     * @return 返回学生信息集合
     */
    @Override
    public List<Student> getStudentByPage(PageUtil pageUtil) {

        String sql = "select * from t_student limit ?,?";
        List<Student> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Student.class),pageUtil.getIndex(),pageUtil.getRows());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取学生信息总条数
     * @return 学生信息总条数
     */
    @Override
    public int getCountRows() {

        String sql = "select count(*) from t_student";
        int countRows = 0;
        try {
            countRows = (int)(long)qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countRows;
    }

    /**
     * 批量删除学生
     * @param stringSid 批量删除学生学号字符串形式
     */
    @Override
    public boolean batchDelete(String stringSid) {
        int rows = 0;
        String sql = "delete from t_student where sid in (" + stringSid + ")";
        try {
            rows = qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows == 1;
    }
}

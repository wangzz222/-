package com.zz.service.impl;

import com.zz.mapper.GradeMapper;
import com.zz.mapper.StudentMapper;
import com.zz.pojo.Grade;
import com.zz.pojo.Student;
import com.zz.service.StudentService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

/*
    业务层：事务的控制 异常处理 资源关闭《----
    持久层：异常抛出
* */


public class StudentServiceImpl implements StudentService {
//    业务层调用Mapper
    @Override
    public List<Student> getStudentList() {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.getStudentList();
        } catch (SQLException e) {
            e.printStackTrace();//异常打印输出
        } finally {
            //不管try中是否有异常
            // finally一定会执行
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;//没有成功返回
    }
    @Override
    public List<Student> getStudentList2(){
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            GradeMapper gradeMapper = sqlSession.getMapper(GradeMapper.class);
            //查询所有学生信息
            List<Student> students = studentMapper.getStudentAll();
            for (Student student : students) {
                //根据学生对象中的gid查询班级信息
                Integer gid = student.getGid();//参数
                Grade grade = gradeMapper.getGradeById(gid);
                //把grade对象设置给student
                student.setGrade(grade);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();//异常打印输出
        } finally {
            //不管try中是否有异常
            // finally一定会执行
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return null;//没有成功返回
    }

    @Override
    public boolean checkName(String sname) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            int count = studentMapper.checkStudent(sname);
            return count <= 0;//ture 可用  false 不可以
        } catch (SQLException e) {
            e.printStackTrace();//异常打印输出
        } finally {
            //不管try中是否有异常
            // finally一定会执行
            MybatisUtil.closeSqlSession();//关闭SqlSession对象
        }
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            //调用studentmapper完成数据添加
            // commit
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentMapper.addStudent(student);
            sqlSession.commit();//事务提交 程序执行成功
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            //回滚
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }
    @Override
    public boolean deleteStudent(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentMapper.deleteStudent(id);
            sqlSession.commit(); // 提交事务
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            sqlSession.rollback(); // 回滚事务
        } finally {
            MybatisUtil.closeSqlSession(); // 关闭SqlSession对象
        }
        return false;
    }
}

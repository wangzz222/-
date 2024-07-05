package com.zz.controller;

import com.zz.pojo.Student;
import com.zz.service.StudentService;
import com.zz.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
查询所有学生信息
 */
@WebServlet("/getStudentList")
public class GetStudentListServlet extends HttpServlet {
    //创建业务层对象
    private  StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentService.getStudentList2();
        System.out.println("studentList" + studentList);
    //带着数据跳转jsp页面展示，转发的方式
        req.setAttribute("slist",studentList);//数据设置到request作用域
        //跳转，使用转发
        req.getRequestDispatcher("/studentList.jsp").forward(req,resp);
    }
}

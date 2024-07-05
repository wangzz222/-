package com.zz.controller;

import com.zz.pojo.Grade;
import com.zz.service.GradeService;
import com.zz.service.impl.GradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
查询班级信息 跳转添加页面
 */
@WebServlet("/toAdd")
public class ToAddServlet extends HttpServlet {

    //定义业务层对象-
    private GradeService gradeService = new GradeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Grade> gradeList = gradeService.getGradeList();
//        设置到request作用域中
        req.setAttribute("gradeList",gradeList);
//        转发
        req.getRequestDispatcher("/addStudent.jsp").forward(req,resp);

    }
}

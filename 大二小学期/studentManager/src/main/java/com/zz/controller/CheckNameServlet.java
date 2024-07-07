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
import java.io.PrintWriter;

/*
验证学生信息是否可用
*/
    @WebServlet("/checkName")
    public class CheckNameServlet extends HttpServlet {

        private StudentService studentService = new StudentServiceImpl();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("utf-8");
            String sname = req.getParameter("sname");

            boolean flag = studentService.checkName(sname);
            //响应对象HttpServletResponse，设置响应的数据类型
            resp.setContentType("text/html;charset=utf-8");
            //获取输出对象
            PrintWriter writer = resp.getWriter();
            writer.print(flag);
            //流对象自己关闭
            writer.flush();
            writer.close();


    }
}

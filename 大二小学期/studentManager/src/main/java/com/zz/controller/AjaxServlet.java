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
import java.util.List;

            /*
            处理ajax异步请求 ， 不能跳转，只是处理的数据相应给客户端
             */
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //响应对象HttpServletResponse，设置响应的数据类型
        resp.setContentType("text/html;charset=utf-8");
        //获取输出对象
        PrintWriter writer = resp.getWriter();
        writer.print("Hello Ajax");
        //流对象自己关闭
        writer.flush();
        writer.close();

    }
}

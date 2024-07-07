package com.zz.controller;

//import
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet 处理请求路径的映射规则
@WebServlet("/login")//可以处理到
public class LoginServlet extends HttpServlet {
////建议可以处理两种请求方式   get  post


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("dogert..........");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost..........");
        System.out.println("req.getParameter() = " + req.getParameter("uname"));
        System.out.println("req.getParameter() = " + req.getParameter("pwd"));
    }


}

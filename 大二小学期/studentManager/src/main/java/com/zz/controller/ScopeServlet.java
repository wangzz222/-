package com.zz.controller;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

        /*
         *  HttpServletRequest 请求对象
         *  HttpServletResponse  响应对象
         * */
@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //超链接 回车
        //request 作用域 请求对象  只能在一次请求中获取
        req.setAttribute("req", "request作用域");
//        req.removeAttribute("req"); //根据key名称移除
        //session作用域  响应对象
        HttpSession session = req.getSession();
        session.setAttribute("sess", "session作用域");
        //Application 整个应用
        ServletContext application = req.getServletContext();
        application.setAttribute("app","application作用域");

        //转发的跳转方式
//        req.getRequestDispatcher("/scope.jsp").forward(req, resp);

        //重定向
        resp.sendRedirect(req.getContextPath()+"/scope.jsp");


    }
}


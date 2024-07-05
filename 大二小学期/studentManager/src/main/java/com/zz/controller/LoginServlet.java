package com.zz.controller;

import com.zz.pojo.User;
import com.zz.service.UserService;
import com.zz.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
登录
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    //定义业务层对象-
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        req.setCharacterEncoding("utf-8");//设置编码格式
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用业务层的方法：
        User u = userService.login(username, password);
        System.out.println("u = " + u) ;
//        判断用户是否登录成功
        if(u != null){//成功把用户信息设置到Session作用域中 req.getSession()获取Session对象
            req.getSession().setAttribute("user", u);
            //跳转到系统内容页面 发送getStudentList
            resp.sendRedirect(req.getContextPath()+"/getStudentList");
        }else{//用户登录失败，返回登录页面
            //重定向：发送的是2次请求，地址栏是改变的 显示的是第二次的地址 如果数据放在request作用域的 不能共享

            resp.sendRedirect(req.getContextPath() + "/login.jsp?flag=false");
            //转发：服务端请求 地址栏不改变 可以共享数据,一般况，表单提交的请求处理完之后不能使用转发
//          req.getRequestDispatcher("/login.jsp").forward(req, resp);

        }
    }
}

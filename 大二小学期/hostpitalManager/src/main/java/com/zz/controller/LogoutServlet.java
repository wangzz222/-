package com.zz.controller;

import com.zz.pojo.Admins;
import com.zz.pojo.Doctors;
import com.zz.service.AdminsService;
import com.zz.service.DoctorsService;
import com.zz.service.impl.AdminsServiceImpl;
import com.zz.service.impl.DoctorsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
 * 处理医院管理登录请求
 * */
@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从请求中获取参数
        String rid = req.getParameter("rid");

        //根据id判断用户的身份  null.equals("")异常
        if("1".equals(rid)) {//管理员
            req.getSession().removeAttribute("admins");
        }else if("2".equals(rid)) {
            req.getSession().removeAttribute("doctors");
        }
        resp.sendRedirect(req.getContextPath() + "/login.jsp");

    }
}

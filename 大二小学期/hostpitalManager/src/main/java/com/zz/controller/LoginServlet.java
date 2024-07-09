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
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    private AdminsService adminsService = new AdminsServiceImpl();
    private DoctorsService doctorsService = new DoctorsServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //从请求中获取参数
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String rid = req.getParameter("rid");

        //根据id判断用户的身份  null.equals("")异常
        if("1".equals(rid)) {//管理员
            //调用管理员
            Admins admins = adminsService.login(name,password);
            if(admins != null) {//成功登陆，
                req.getSession().setAttribute("admins", admins);
                //重定向到管理员的页面
                resp.sendRedirect(req.getContextPath()+"/adminIndex.jsp");
            }else{
                //重定向到登录页
                resp.sendRedirect(req.getContextPath()+"/login.jsp?flag=false");
            }
        }else if("2".equals(rid)) {
            //医生
            Doctors doctors = doctorsService.login(name,password);
            if(doctors != null) {
                req.getSession().setAttribute("doctors", doctors);
                //重定向到医生的页面
                resp.sendRedirect(req.getContextPath()+"/doctorIndex.jsp");
            }else {
                resp.sendRedirect(req.getContextPath()+"/login.jsp?flag=false");
            }
        }

    }
}

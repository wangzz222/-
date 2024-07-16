package com.zz.controller.depart;

import com.github.pagehelper.PageInfo;
import com.zz.pojo.Departments;
import com.zz.service.DepartmentsService;
import com.zz.service.impl.DepartmentsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述： 科室的添加请求
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/depart/addDepart")
public class AddDepartServlet extends HttpServlet {
    private DepartmentsService departmentsService = new DepartmentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String departname = req.getParameter("departname");
        String departdesc = req.getParameter("departdesc");
        String pid = req.getParameter("pid");
        //把参数封装成对象
        Departments departments = new Departments();
        departments.setDepartmentName(departname);
        departments.setDepartmentDescription(departdesc);
        if(pid!=null && !pid.equals("")){
            int dpid = Integer.valueOf(pid);
            departments.setDepartmentPid(dpid);//设置fu级id
            if(dpid==0){//父级id=0都是一级科室
                departments.setDepartmentLevel(1);
            }else {
                departments.setDepartmentLevel(2);
            }

        }
        boolean flag = departmentsService.addDepartMent(departments);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/depart/getDepartList");
        }

    }

}

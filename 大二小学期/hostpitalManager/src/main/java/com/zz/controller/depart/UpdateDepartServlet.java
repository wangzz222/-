package com.zz.controller.depart;

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
@WebServlet("/depart/updateDepart")
public class UpdateDepartServlet extends HttpServlet {
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
        String id = req.getParameter("id");
        //把参数封装成对象
        Departments departments = new Departments();
        departments.setDepartmentName(departname);
        departments.setDepartmentDescription(departdesc);
        if(id!=null && !id.equals("")){
            departments.setDepartmentId(Integer.parseInt(id));
        }
        boolean flag = departmentsService.updateDepartMent(departments);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/depart/getDepartList");
        }

    }

}

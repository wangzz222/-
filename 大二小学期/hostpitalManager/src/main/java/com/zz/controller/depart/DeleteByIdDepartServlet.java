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
 * 功能描述： 科室的删除
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/depart/deleteById")
public class DeleteByIdDepartServlet extends HttpServlet {
    private DepartmentsService departmentsService = new DepartmentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String id = req.getParameter("id");
        boolean flag = departmentsService.deleteById(id);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/depart/getDepartList");
        }

    }

}

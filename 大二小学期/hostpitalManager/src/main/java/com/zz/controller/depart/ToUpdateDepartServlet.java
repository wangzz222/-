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
 * 功能描述： 跳转修改页面，根据逐渐id
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/depart/toUpdate")
public class ToUpdateDepartServlet extends HttpServlet {
    private DepartmentsService departmentsService = new DepartmentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String did = req.getParameter("did");
        //调用业务层，根据主键id返回对象
        Departments departments = departmentsService.getDepartmentById(did);
        //把科室对象设置到Request作用域中
        req.setAttribute("departments", departments);
        //跳转修改页面
        req.getRequestDispatcher("/updateDepart.jsp").forward(req, resp);

    }

}

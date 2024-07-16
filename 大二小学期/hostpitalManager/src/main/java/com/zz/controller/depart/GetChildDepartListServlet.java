package com.zz.controller.depart;

import com.alibaba.fastjson.JSON;
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
import java.io.PrintWriter;
import java.util.List;

/**
 * 功能描述：根据pid查询返回所有的子集科室李彪，，使用异步请求，需要把数据输出给客户端
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/depart/getChildDepartList")
public class GetChildDepartListServlet extends HttpServlet {
    private DepartmentsService departmentsService = new DepartmentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        List<Departments> dlist = departmentsService.getDepartListAll(pid);
        System.out.println("dlist = "+dlist);
        //先获取输出对象
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(dlist));
        out.flush();
        out.close();
    }

}

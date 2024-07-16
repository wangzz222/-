package com.zz.controller.depart;

import com.github.pagehelper.PageInfo;
import com.zz.service.DepartmentsService;
import com.zz.service.impl.DepartmentsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/depart/getDepartList")
public class GetDepartListServlet extends HttpServlet {
    private DepartmentsService departmentsService = new DepartmentsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //分页；limit 0,3 第一页 limit 3,3 第二页
        //获取请求中用户请求当前页 page
        String page = req.getParameter("page");//用户请求的当前页
        //业务层返回PageInfo对象：包含页面需要分页信息，集合列表
        PageInfo pageInfo = departmentsService.getDepartListPage(page,0);//查询pid等于0一级科室列表
        //跳转jsp展示数据  把需要展示的数据设置到Request作用域中
        req.setAttribute("pageInfo", pageInfo);
        //使用转发的跳转方式
        req.getRequestDispatcher("/departList.jsp").forward(req, resp);
    }

}

package com.zz.controller.doctor;

import com.github.pagehelper.PageInfo;
import com.zz.pojo.DoctorsQuery;
import com.zz.service.DoctorsService;
import com.zz.service.impl.DoctorsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述： 处理分页多条件的处理请求
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/doctor/getDoctorList")
public class GetDoctorListServlet extends HttpServlet {

    private  DoctorsService doctorService = new DoctorsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String did = req.getParameter("did");//
        String pid = req.getParameter("pid");//
        String jobnum = req.getParameter("jobnum");//
        String dname = req.getParameter("dname");//
        String page = req.getParameter("page");//
        
        //创建对象封装查询参数
        DoctorsQuery doctorsQuery = new DoctorsQuery(did, pid, jobnum, dname, page);
        //调用业务层查询的方法
        PageInfo pageInfo = doctorService.getDoctorListPage(doctorsQuery);
        //把分页对象设置到Request作用域中
        req.setAttribute("pageInfo", pageInfo);
        req.setAttribute("doctorsQuery", doctorsQuery);
        System.out.println("pageinfo = " + pageInfo);
                req.getRequestDispatcher("/doctorList.jsp").forward(req, resp);


    }

}

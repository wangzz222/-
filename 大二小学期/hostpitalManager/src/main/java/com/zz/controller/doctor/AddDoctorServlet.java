package com.zz.controller.doctor;

import com.zz.service.DoctorsService;
import com.zz.service.impl.DoctorsServiceImpl;

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
@WebServlet("/doctor/addDoctor")
public class AddDoctorServlet extends HttpServlet {

    private DoctorsService doctorService = new DoctorsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String cid = req.getParameter("cid");//科室id
        String num = req.getParameter("num");//生成账号数量

        boolean flag = doctorService.addDoctors(cid,num);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/depart/getDepartList");
        }

    }

}

package com.zz.controller.doctor;

import com.zz.pojo.Doctors;
import com.zz.service.DoctorsService;
import com.zz.service.impl.DoctorsServiceImpl;
import com.zz.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 功能描述： 医生信息修改更新
 * 作者：wzz
 * 时间：2024-07-08
 */
@WebServlet("/doctor/updateDoctor")
@MultipartConfig
public class UpdateDoctorServlet extends HttpServlet {

    private DoctorsService doctorService = new DoctorsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的参数
        String jobNumber = req.getParameter("jobNumber");  //工号
        String name = req.getParameter("name");  //医生的姓名
        String phone = req.getParameter("phone");  //手机号
        String email = req.getParameter("email"); //邮箱
        String registrationFee = req.getParameter("registrationFee"); //挂号费
        String introduction = req.getParameter("introduction");//个人简介
        String pid = req.getParameter("pid");//职称id
        String entryDate = req.getParameter("entryDate");//入职时间
        //调用业务层的方法完成信息修改的功能
        //封装参数
        Doctors doctors = new Doctors(jobNumber, name, phone, email, introduction, registrationFee, entryDate, pid);
        //不一定每一次都提交一个文件，需要判断表单中是否包含文件上传
        Part part = req.getPart("file");
        if (part != null && part.getSize() > 0) {//需要处理文件上传
            //返回文件上传成功后的路径
            String file = FileUtil.transferTo(req, "file");
            //设置到doctors对象中
            doctors.setAvatar(file);
        }
        //调用业务层方法完成医生信息的修改功能
        boolean flag = doctorService.updateDoctorByJobNumber(doctors);
        if(flag){
            //替换session中信息
            resp.sendRedirect(req.getContextPath()+"/doctorsIndex.jsp");
        }
    }

}

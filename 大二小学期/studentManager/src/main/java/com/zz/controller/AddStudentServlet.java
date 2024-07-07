package com.zz.controller;

import com.zz.pojo.Student;
import com.zz.service.StudentService;
import com.zz.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

                /**
                **  添加学生信息
                **/

    @WebServlet("/addStudent")
    public class AddStudentServlet extends HttpServlet {

        StudentService studentService = new StudentServiceImpl();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req,resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // 设置请求编码格式
            req.setCharacterEncoding("UTF-8");
            //响应对象HttpServletResponse，设置响应的数据类型
            resp.setContentType("text/html;charset=utf-8");
            //trim()函数可以去掉字符串前后的空格
            String sname = req.getParameter("sname").trim();
            String age = req.getParameter("age");
            String sex = req.getParameter("sex");
            String phone = req.getParameter("phone");
            String gid = req.getParameter("gid");
            //请求中获取到的参数String，
            //把参数封装给student对象
            Student student = new Student();
            student.setSname(sname);
            //类型转换之前必须先判断是否为空 为NULL不能转换
            if(age != null && !age.equals("")){
                student.setAge(Integer.parseInt(age));
            }
            if(gid != null && !gid.equals("")){
                student.setGid(Integer.parseInt(gid));
            }
            student.setPhone(phone);
            student.setSex(sex);
            //调用业务层添加学生信息
            boolean flag = studentService.addStudent(student);
            //添加成功，列表页面
            if(flag){
                resp.sendRedirect(req.getContextPath() + "/getStudentList");
            }
        }
    }

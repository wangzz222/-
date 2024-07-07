package com.zz.controller;


import com.zz.service.StudentService;
import com.zz.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

            /*
            删除学生信息
             */
@WebServlet("/deleteStudent")
public class DeleteServlet extends HttpServlet {

    //定义业务层对象-
    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要删除的学生ID
        String idStr = req.getParameter("sid");
        if (idStr != null && !idStr.isEmpty()) {
            int sid = Integer.parseInt(idStr);
            // 调用业务层删除学生
            boolean success = studentService.deleteStudent(sid);
            if (success) {
                // 删除成功后重定向到学生列表页面
                resp.sendRedirect(req.getContextPath() + "/getStudentList");
            } else {
                // 处理删除失败的情况
                resp.getWriter().write("删除失败");
            }
        } else {
            // 处理ID无效的情况
            resp.getWriter().write("无效的学生ID");

        }
    }
}

package com.zz.controller.listener;

import com.zz.pojo.Departments;
import com.zz.pojo.ProfessionalTitles;
import com.zz.service.DepartmentsService;
import com.zz.service.ProfessionalTitlesService;
import com.zz.service.impl.DepartmentsServiceImpl;
import com.zz.service.impl.ProfessionalTitlesServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * 功能描述：自定义监听器，监听application作用域对象的创建
 * 作者：wzz
 * 时间：2024/07/12 13:34
 */
@WebListener
public class MyWebApplicationListener implements ServletContextListener {
    //创建科室业务层对象和职称的对象
    private DepartmentsService departmentsService = new DepartmentsServiceImpl();
    private ProfessionalTitlesService professionalTitlesService = new ProfessionalTitlesServiceImpl();
    //对象创建时被调用
    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("监听application作用域对象的创建"+sce);

        List<ProfessionalTitles> professionalTitlesList = professionalTitlesService.getProfessionalTitlesList();
        List<Departments> dListLevelfirst = departmentsService.getDepartListLevel(1);
        List<Departments> dListLevelcsecond = departmentsService.getDepartListLevel(2);
        ServletContext application = sce.getServletContext();
        application.setAttribute("professionalTitlesList", professionalTitlesList);
        application.setAttribute("dListLevelfirst", dListLevelfirst);
        application.setAttribute("dListLevelcsecond", dListLevelcsecond);

        //查询科室信息,只返回二级科室


    }
}

package com.zz.service;

import com.github.pagehelper.PageInfo;
import com.zz.pojo.Departments;

import java.util.List;


public interface DepartmentsService {
    PageInfo getDepartListPage(String page, int pid);

    List<Departments> getDepartListAll(String pid);

    boolean addDepartMent(Departments departments);

    Departments getDepartmentById(String did);

    boolean updateDepartMent(Departments departments);

    boolean deleteById(String id);

    List<Departments> getDepartListLevel(Integer level);

}

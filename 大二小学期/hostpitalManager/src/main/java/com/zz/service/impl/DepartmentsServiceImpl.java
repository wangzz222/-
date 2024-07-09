package com.zz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.mapper.DepartmentsMapper;
import com.zz.pojo.Departments;
import com.zz.service.DepartmentsService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * 功能描述：科室的业务层
 * 作者：wzz
 * 时间：2024-07-08
 */

public class DepartmentsServiceImpl implements DepartmentsService {
    /**
     * @discription:
     * @author: wzz
     * @data: 2024/26/08 21:26
     * @param: []       page 用户请求的当前页
     * @return: com.github.pagehelper.PageInfo
     **/
    @Override
    public PageInfo getDepartListPage(String page,int pid) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            //使用分页插件进行分页查询
            //pageNum:用户请求的当前页 pageSize：每页显示的记录数 3，5
            if(page != null && !"".equals(page)){
                PageHelper.startPage(Integer.valueOf(page),5);//开始分页
            }else {
                PageHelper.startPage(1,5);//开始分页,默认返回第一页数据
            }
            //紧跟开始分页代码后面的第一个查询默认会自动分页
            List<Departments> departList = departmentsMapper.getDepartList(pid);
            //创建分页对象，设置集合到分页对象中
            PageInfo<Departments> pageInfo = new PageInfo<>(departList);
            System.out.println("pageInfo = " + pageInfo);
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;

    }
}

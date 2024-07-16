package com.zz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.mapper.DepartmentsMapper;
import com.zz.pojo.Departments;
import com.zz.service.DepartmentsService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

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
            //循环遍历科室的集合，添加是否有下级科室的属性值
            for(Departments depart:departList){
                Integer departmentId = depart.getDepartmentId();
                //根据id查询是否有下级
                int count = departmentsMapper.getChildCount(departmentId);
                if(count > 0){//有子科室
                    depart.setHashchild(true);
                }else {
                    depart.setHashchild(false);
                }
            }
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

    @Override
    public List<Departments> getDepartListAll(String pid) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            //紧跟开始分页代码后面的第一个查询默认会自动分页
            List<Departments> departList = departmentsMapper.getDepartList(Integer.parseInt(pid));
            return departList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;

    }
    /**
     * @discription: 添加科室的信息
     * @author: wzz
     * @data: 2024/07/09 20:09
     * @param: [departments]
     * @return: boolean
     **/

    @Override
    public boolean addDepartMent(Departments departments) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            departmentsMapper.addDepartMent(departments);
            sqlSession.commit();//提交事务
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        }finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }
/**
 * @discription: 修改 根据主键id查询返回对象
 * @author: wzz
 * @data: 7/10/周三 14:56
 * @param: [did]
 * @return: com.zz.pojo.Departments
 **/
    @Override
    public Departments getDepartmentById(String did) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            Departments departments = departmentsMapper.getDepartmentById(did);
            return departments;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public boolean updateDepartMent(Departments departments) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            departmentsMapper.updateDepartMent(departments);
            sqlSession.commit();//提交事务
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        }finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            departmentsMapper.deleteById(id);
            sqlSession.commit();//提交事务
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        }finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }



    @Override
    public List<Departments> getDepartListLevel(Integer level) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            DepartmentsMapper departmentsMapper = sqlSession.getMapper(DepartmentsMapper.class);
            return departmentsMapper.getDepartListLevel(level);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }


}

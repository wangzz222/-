package com.zz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.mapper.DoctorsMapper;
import com.zz.pojo.Doctors;
import com.zz.pojo.DoctorsQuery;
import com.zz.service.DoctorsService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.sql.SQLException;
import java.util.List;

public class DoctorsServiceImpl implements DoctorsService {
    @Override
    public Doctors login(String name, String password) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorsMapper doctorsMapper = sqlSession.getMapper(DoctorsMapper.class);
            return doctorsMapper.login(name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public PageInfo getDoctorListPage(DoctorsQuery doctorsQuery) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorsMapper doctorsMapper = sqlSession.getMapper(DoctorsMapper.class);
            //分页查询
            String page = doctorsQuery.getPage();
            if (page != null && "".equals(page)) {
                PageHelper.startPage(Integer.valueOf(page), 5);
            } else {
                PageHelper.startPage(1, 5);
            }
            //紧跟的第一个查询会被自动分页
            List<Doctors> dlist = doctorsMapper.getDoctorList(doctorsQuery);
            //创建分页对象，封装
            PageInfo pageInfo = new PageInfo(dlist);
            return pageInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

    @Override
    public boolean addDoctors(String cid, String num) {

        //cid 科室id ； job_number 自动生成 password = 123456
        //1.查询jobnum最大值
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorsMapper doctorsMapper = sqlSession.getMapper(DoctorsMapper.class);
            String jobnumber = doctorsMapper.getJobNumberMax();
            int jobnum = Integer.parseInt(jobnumber);
            for (int i = 1; i <= Integer.parseInt(num); i++) {
                doctorsMapper.addDoctor(cid, ++jobnum);
            }
            sqlSession.commit();//提交事务
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean deleteDoctorById(String id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorsMapper doctorsMapper = sqlSession.getMapper(DoctorsMapper.class);
            doctorsMapper.deleteById(id);
            sqlSession.commit();//提交事务
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();//事务回滚
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }

    @Override
    public boolean updateDoctorByJobNumber(Doctors doctors) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try {
            DoctorsMapper doctorsMapper = sqlSession.getMapper(DoctorsMapper.class);
            doctorsMapper.updateDoctorByJobNumber(doctors);
            sqlSession.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return false;
    }
}
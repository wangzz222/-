package com.zz.service.impl;

import com.zz.mapper.DoctorsMapper;
import com.zz.pojo.Doctors;
import com.zz.service.DoctorsService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.sql.SQLException;

public class DoctorsServiceImpl implements DoctorsService {
    @Override
    public Doctors login(String name, String password) {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            DoctorsMapper doctorsMapper = sqlSession.getMapper(DoctorsMapper.class);
            return doctorsMapper.login(name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }
}

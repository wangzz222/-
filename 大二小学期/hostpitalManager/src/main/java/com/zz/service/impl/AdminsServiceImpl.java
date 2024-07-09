package com.zz.service.impl;

import com.zz.mapper.AdminsMapper;
import com.zz.pojo.Admins;
import com.zz.service.AdminsService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class AdminsServiceImpl implements AdminsService {
    @Override
    public Admins login(String name, String password)  {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            AdminsMapper adminsMapper = sqlSession.getMapper(AdminsMapper.class);
            return adminsMapper.login(name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }
}

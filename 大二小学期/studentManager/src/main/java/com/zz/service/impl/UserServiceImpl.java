package com.zz.service.impl;

import com.zz.mapper.UserMapper;
import com.zz.pojo.User;
import com.zz.service.UserService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public User login(String username,String password){
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            return userMapper.login(username,password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return userMapper.login2(user);
        } catch (SQLException e) {
           e.printStackTrace();
        }  finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

}

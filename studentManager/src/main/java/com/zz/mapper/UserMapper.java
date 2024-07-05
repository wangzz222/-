package com.zz.mapper;

import com.zz.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

public interface UserMapper {
    //在
    //如果方法接收到多个参数，系统会给多个参数，自动取名[arg0 arg1 param1 param2]
   // @Select("select * from user where username=#{arg0} and password=#{arg1}")
    //  @Select("select * from user where username=#{param1} and password=#{param2}")
    //使用@Param()可以给参数取名称
    @Select("select * from user where username=#{uname} and password=#{password}")
    User login(@Param("uname") String username,@Param("password") String password) throws SQLException;

    //参数是对象，直接使用 获取参数
    @Select("select * from user where username=#{username} and password=#{password}")
    User login2(User user) throws SQLException;
    //mybatis 接口中的方法 如果有多个参数处理的方式
//    1.使用@Param注解
//    2.把多个参数发封装给自定义对象，可以使用对象、属性获取5
}

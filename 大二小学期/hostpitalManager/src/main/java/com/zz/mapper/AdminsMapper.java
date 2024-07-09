package com.zz.mapper;

import com.zz.pojo.Admins;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

public interface AdminsMapper {

    @Select("select * from admins where username=#{name} and password = #{pwd}")
    Admins login(@Param("name") String name,@Param("pwd") String password) throws SQLException;
}

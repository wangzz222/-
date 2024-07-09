package com.zz.mapper;


import com.zz.pojo.Doctors;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

public interface DoctorsMapper {

    @Select("select * from doctors where job_number=#{jobNumber} and password=#{password}")
    Doctors login(@Param("jobNumber") String name, @Param("password") String password) throws SQLException;
}

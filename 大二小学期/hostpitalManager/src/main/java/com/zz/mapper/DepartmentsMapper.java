package com.zz.mapper;

import com.github.pagehelper.PageInfo;
import com.zz.pojo.Departments;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentsMapper {
    @Select("select * from departments where department_pid=#{pid} ")
    List<Departments> getDepartList(int pid) throws SQLException;
}

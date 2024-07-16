package com.zz.mapper;

import com.zz.pojo.Departments;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentsMapper {
    @Select("select * from departments where department_pid=#{pid} ")
    List<Departments> getDepartList(int pid) throws SQLException;

    @Insert("INSERT into departments(department_name,department_pid,department_level,department_description) VALUES(#{departmentName},#{departmentPid},#{departmentLevel},#{departmentDescription})")
    void addDepartMent(Departments departments) throws SQLException;

    @Select("select * from departments where department_id = #{did}")
    Departments getDepartmentById(@Param("did") String did) throws SQLException;

    @Update("update departments set department_name=#{departmentName},department_description=#{departmentDescription} where department_id=#{departmentId}")
    void updateDepartMent(Departments departments);

    @Delete("delete from departments where department_id=#{id}")
    void deleteById(@Param("id") String id);

    @Select("select count(*) from departments where department_pid=${id}")
    int getChildCount(Integer departmentId);

    @Select("select * from departments where department_level=${level}")
    List<Departments> getDepartListLevel(Integer level) throws SQLException;
}

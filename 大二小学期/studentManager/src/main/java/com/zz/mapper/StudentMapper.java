package com.zz.mapper;

import com.zz.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

public interface StudentMapper {
   List<Student> getStudentList() throws SQLException;
   List<Student> getStudentAll() throws SQLException;
   @Insert("insert into student(sname,age,sex,phone,gid) values (#{sname},#{age},#{sex},#{phone},#{gid})")
   int addStudent(Student student) throws SQLException;

   @Select("select count(sid) from student where sname=#{name}")
   int checkStudent(@Param("name")String name) throws SQLException;

   @Delete("delete from student where sid=#{sid}")
   int deleteStudent(@Param("sid")int sid) throws SQLException;

}

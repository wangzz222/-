package com.zz.mapper;

import com.zz.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentMapper {
   List<Student> getStudentList() throws SQLException;
   List<Student> getStudentAll() throws SQLException;
}

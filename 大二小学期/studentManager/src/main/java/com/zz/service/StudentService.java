package com.zz.service;

import com.zz.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList() ;
    List<Student> getStudentList2() ;
    boolean checkName(String sname);
    boolean addStudent(Student student);
    boolean deleteStudent(int id);
}

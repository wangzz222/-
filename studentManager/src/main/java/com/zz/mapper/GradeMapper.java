package com.zz.mapper;

import com.zz.pojo.Grade;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GradeMapper {
    //根据id查询班级对象
    //使用场景：简单的CRUD
    @Select("select * from grade where gid = #{id}")
    Grade getGradeById(int id);

    @Select("select * from grade")
    List<Grade> getGradeList();
}

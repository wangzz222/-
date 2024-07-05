package com.zz.service.impl;

import com.zz.mapper.GradeMapper;
import com.zz.pojo.Grade;
import com.zz.service.GradeService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GradeServiceImpl implements GradeService {

    @Override
    public List<Grade> getGradeList() {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            GradeMapper gradeMapper = sqlSession.getMapper(GradeMapper.class);
            return gradeMapper.getGradeList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }
}

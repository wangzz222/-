package com.zz.service.impl;

import com.zz.mapper.ProfessionalTitlesMapper;
import com.zz.pojo.ProfessionalTitles;
import com.zz.service.ProfessionalTitlesService;
import com.zz.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 功能描述：
 * 作者：wzz
 * 时间：2024/07/12 13:44
 */

public class ProfessionalTitlesServiceImpl implements ProfessionalTitlesService {


    @Override
    public List<ProfessionalTitles> getProfessionalTitlesList() {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            ProfessionalTitlesMapper professionalTitlesMapper= sqlSession.getMapper(ProfessionalTitlesMapper.class);
            return professionalTitlesMapper.getProfessionalTitlesList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return null;
    }

}

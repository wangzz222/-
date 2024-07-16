package com.zz.mapper;

import com.zz.pojo.ProfessionalTitles;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

public interface ProfessionalTitlesMapper {
    @Select("select * from professional_titles")
    List<ProfessionalTitles> getProfessionalTitlesList() throws SQLException;
}

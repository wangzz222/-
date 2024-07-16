package com.zz.mapper;


import com.zz.pojo.Doctors;
import com.zz.pojo.DoctorsQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

public interface DoctorsMapper {

    @Select("select * from doctors where job_number=#{jobNumber} and password=#{password}")
    Doctors login(@Param("jobNumber") String name, @Param("password") String password) throws SQLException;


    @Select("select max(job_number) from doctors")
    String getJobNumberMax() throws SQLException; ;

    @Insert("INSERT into doctors(job_number,password,department_id) values(#{jobnum},'123456',#{did})")
    void addDoctor(@Param("did") String cid,@Param("jobnum") int jobnum) throws SQLException;

    List<Doctors> getDoctorList(DoctorsQuery doctorsQuery) throws SQLException;

    //标记删除
    @Update("update doctors set state=1 where doctor_id=#{id}")
    void deleteById(@Param("id") String id) throws SQLException;

    @Update("update doctors set name=#{name},avatar=#{avatar},phone=#{phone},email=#{email},introduction=#{introduction},registration_fee=#{registrationFee},entry_date=#{entryDate},professional_title_id=#{professionalTitleId} where job_number=#{jobNumber}")
    void updateDoctorByJobNumber(Doctors doctors) throws SQLException;
}

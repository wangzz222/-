package com.zz.service;

import com.github.pagehelper.PageInfo;
import com.zz.pojo.Doctors;
import com.zz.pojo.DoctorsQuery;


public interface DoctorsService {
    Doctors login(String name, String password);
    PageInfo getDoctorListPage(DoctorsQuery doctorsQuery);

    boolean addDoctors(String cid, String num);

    boolean deleteDoctorById(String id);

    boolean updateDoctorByJobNumber(Doctors doctors);
}

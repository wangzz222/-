package com.zz.pojo;

import java.sql.Date;

public class  Doctors {
    String doctorId;

    String jobNumber;

    String password;

    String name;

    String avatar;

    String phone;

    String email;

    String introduction;

    String registration;

    String entry_date;

    String department_id;

    String professional_title_id;

    private Departments departments;//部门对象

    private ProfessionalTitles professionalTitles;//职称对象

    private Integer state;

    public Doctors(String jobNumber, String name, String phone, String email, String introduction, String registrationFee, String entryDate, String pid) {
        this.jobNumber = jobNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.introduction = introduction;
        this.registration = registrationFee;
        this.entry_date = entryDate;
        this.professional_title_id = pid;

    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = String.valueOf(entry_date);
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }


    public void setProfessional_title_id(String professional_title_id) {
        this.professional_title_id = professional_title_id;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }


    public Departments getDepartment() {
        return departments;
    }

    public void setDepartment(Departments department) {
        this.departments = department;
    }

    public ProfessionalTitles getProfessionalTitles() {
        return professionalTitles;
    }

    public void setProfessionalTitles(ProfessionalTitles professionalTitles) {
        this.professionalTitles = professionalTitles;
    }
}

package com.zz.pojo;

/**
 * 功能描述：医生的查询对象
 * 作者：wzz
 * 时间：2024/07/13 15:34
 */

public class DoctorsQuery {
    private String did;//科室的id

    private String pid;//职称的id
    private String dname;//医生的姓名
    private String jobnum;//医生工号
    private String page;//当前页

    public DoctorsQuery(String did, String pid, String dname, String jobnum, String page) { 
        this.did = did;
        this.pid = pid;
        this.dname = dname;
        this.jobnum = jobnum;
        this.page = page;
    }

    public DoctorsQuery() {

    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getJobnum() {
        return jobnum;
    }

    public void setJobnum(String jobnum) {
        this.jobnum = jobnum;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }


}

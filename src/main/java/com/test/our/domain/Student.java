package com.test.our.domain;

import java.util.Date;

public class Student {
    private Integer id;

    private String name;

    private String sex;

    private String birth;

    private String department;

    private Date create_time;

    private String hd_symble;

    private Date deal_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getHd_symble() {
        return hd_symble;
    }

    public void setHd_symble(String hd_symble) {
        this.hd_symble = hd_symble == null ? null : hd_symble.trim();
    }

    public Date getDeal_time() {
        return deal_time;
    }

    public void setDeal_time(Date deal_time) {
        this.deal_time = deal_time;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", department='" + department + '\'' +
                ", create_time=" + create_time +
                ", hd_symble='" + hd_symble + '\'' +
                ", deal_time=" + deal_time +
                '}';
    }
}
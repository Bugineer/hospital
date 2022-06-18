package com.qf.doctor.pojo;

import java.util.Date;

/**
 * 医生表
 * @author Salted Fish
 * @create 2022-06-16 8:37
 */
public class Doctor {
    private Integer did;
    private String name;
    private String cardno;
    private String phone;
    private Integer sex;
    private Integer age;
    private Date    birthday;
    private Date hireDate;
    private String email;
    private Integer department;
    private Integer education;
    private String remark;

    private Education edu;
    private Policlinic policlinic;


    public Doctor() {
    }

    public Doctor(Integer did, String name, String cardno, String phone, Integer sex, Integer age, Date birthday, Date hireDate, String email, Integer department, Integer education, String remark) {
        this.did = did;
        this.name = name;
        this.cardno = cardno;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.hireDate = hireDate;
        this.email = email;
        this.department = department;
        this.education = education;
        this.remark = remark;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Education getEdu() {
        return edu;
    }

    public void setEdu(Education edu) {
        this.edu = edu;
    }

    public Policlinic getPoliclinic() {
        return policlinic;
    }

    public void setPoliclinic(Policlinic policlinic) {
        this.policlinic = policlinic;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "did=" + did +
                ", name='" + name + '\'' +
                ", cardno='" + cardno + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthday=" + birthday +
                ", hireDate=" + hireDate +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", education=" + education +
                ", remark='" + remark + '\'' +
                ", edu=" + edu +
                ", policlinic=" + policlinic +
                '}';
    }
}

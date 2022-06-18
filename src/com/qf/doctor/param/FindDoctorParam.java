package com.qf.doctor.param;

/**
 * @author Salted Fish
 * @create 2022-06-16 9:24
 */
public class FindDoctorParam {
    private String did;
    private String name;
    private String department;

    public FindDoctorParam() {
    }

    public FindDoctorParam(String did, String name, String department) {
        this.did = did;
        this.name = name;
        this.department = department;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

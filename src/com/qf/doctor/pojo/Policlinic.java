package com.qf.doctor.pojo;

/**
 * 科室表
 * @author Salted Fish
 * @create 2022-06-16 8:37
 */
public class Policlinic {
    private Integer pid;
    private String pname;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Policlinic{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                '}';
    }
}

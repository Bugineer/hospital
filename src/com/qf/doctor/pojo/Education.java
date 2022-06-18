package com.qf.doctor.pojo;

/**
 * 学历表
 * @author Salted Fish
 * @create 2022-06-16 8:37
 */
public class Education {
    private Integer eid;
    private String ename;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Override
    public String toString() {
        return "Education{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                '}';
    }
}

package com.qf.hospital.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 23:46
 */
public class Register {
    private String rid;
    private String name;
    private Double registerMoney;
    private Date registerDate;


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(Double registerMoney) {
        this.registerMoney = registerMoney;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "Register{" +
                "rid='" + rid + '\'' +
                ", name='" + name + '\'' +
                ", registerMoney=" + registerMoney +
                ", registerDate=" + registerDate +
                '}';
    }
}

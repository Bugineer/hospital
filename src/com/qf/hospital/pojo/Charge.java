package com.qf.hospital.pojo;

import java.util.Date;

/**
 * @author Salted Fish
 * @create 2022-06-17 11:28
 */
public class Charge {
    private Integer cid;
    private String cname;
    private Double chargeAmount;
    private Date chargeDate;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Date getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
    }

    @Override
    public String toString() {
        return "Charge{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", chargeAmount=" + chargeAmount +
                ", chargeDate=" + chargeDate +
                '}';
    }
}

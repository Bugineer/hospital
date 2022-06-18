package com.qf.hospital.pojo;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.Date;

/**
 * 病人 与 收费项目 关系 表
 * @author Salted Fish
 * @create 2022-06-17 14:22
 */
public class ChargeAndRegister {
    private Integer id;
    private String rid;
    private Integer cid;
    private Date createDate;

    //关联对象
    private Charge charge;
    private Register register;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "ChargeAndRegister{" +
                "id=" + id +
                ", rid='" + rid + '\'' +
                ", cid=" + cid +
                ", createDate=" + createDate +
                ", charge=" + charge +
                ", register=" + register +
                '}';
    }
}

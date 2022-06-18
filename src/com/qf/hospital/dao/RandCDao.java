package com.qf.hospital.dao;

import com.qf.hospital.pojo.ChargeAndRegister;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 14:26
 */
public interface RandCDao {

    List<ChargeAndRegister> findByPage(String rid,Integer index,Integer pageSize);

    Long countByPageWhere(String rid);

    Double countMoney(String rid);

    boolean addCharge(String rid, int cid);
}

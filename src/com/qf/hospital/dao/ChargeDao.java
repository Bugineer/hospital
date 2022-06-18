package com.qf.hospital.dao;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.qf.hospital.pojo.Charge;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 11:43
 */
public interface ChargeDao {
    Charge findByCid(Integer cid);

    List<Charge> findByName(String name);

}

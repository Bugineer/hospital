package com.qf.hospital.dao;

import com.qf.hospital.pojo.Register;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 23:58
 */
public interface RegisterDao {
    public List<Register> findRegisterByPageWhere(String id,String name,Integer index,Integer pageSize);

    public Long countByPageWhere(String id,String name);

    Register findById(String id);
}

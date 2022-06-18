package com.qf.doctor.dao;

import com.qf.doctor.pojo.Education;
import com.qf.doctor.pojo.Policlinic;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 8:36
 */
public interface PoliclinicDao {

    Policlinic findById(Integer id);

    List<Policlinic> findAll();

    Education findEduById(Integer id);
    List<Education> findEduAll();
}

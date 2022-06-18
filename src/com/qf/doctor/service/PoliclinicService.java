package com.qf.doctor.service;

import com.qf.doctor.pojo.Education;
import com.qf.doctor.pojo.Policlinic;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 10:11
 */
public interface PoliclinicService {

    List<Policlinic> queryAll();

    List<Education> queryEduAll();

}

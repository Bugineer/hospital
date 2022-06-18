package com.qf.doctor.dao;

import com.qf.doctor.param.FindDoctorParam;
import com.qf.doctor.pojo.Doctor;


import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-15 15:40
 */
public interface DoctorDao {

    List<Doctor> findDoctorByPageWhere(FindDoctorParam param, Integer index, Integer pageSize);

    Long countByPageWhere(FindDoctorParam param);

    Doctor findById(int did);

    boolean updateDoctor(Doctor doctor);

    boolean addDoctor(Doctor doctor);

}

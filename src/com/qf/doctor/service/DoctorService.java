package com.qf.doctor.service;

import com.qf.common.util.PageUtils;
import com.qf.doctor.param.FindDoctorParam;
import com.qf.doctor.pojo.Doctor;
import com.qf.role.pojo.Role;

/**
 * @author Salted Fish
 * @create 2022-06-15 15:41
 */
public interface DoctorService {

    PageUtils<Doctor> queryDoctorByPage(FindDoctorParam findParam, Integer pageNo, Integer pageSize);

    Doctor queryById(int parseInt);

    boolean modifyDoctor(Doctor doctor);

    boolean saveDoctor(Doctor doctor);

}

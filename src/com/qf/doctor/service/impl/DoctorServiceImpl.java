package com.qf.doctor.service.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.common.util.PageUtils;
import com.qf.doctor.dao.DoctorDao;
import com.qf.doctor.dao.PoliclinicDao;
import com.qf.doctor.dao.impl.DoctorDaoImpl;
import com.qf.doctor.dao.impl.PoliclinicDaoImpl;
import com.qf.doctor.param.FindDoctorParam;
import com.qf.doctor.pojo.Doctor;
import com.qf.doctor.pojo.Education;
import com.qf.doctor.pojo.Policlinic;
import com.qf.doctor.service.DoctorService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 9:28
 */
public class DoctorServiceImpl implements DoctorService {

    private DoctorDao doctorDao = new DoctorDaoImpl();
    private PoliclinicDao policlinicDao = new PoliclinicDaoImpl();

    @Override
    public PageUtils<Doctor> queryDoctorByPage(FindDoctorParam findParam, Integer pageNo, Integer pageSize) {

        Long count = doctorDao.countByPageWhere(findParam);
        int totalPageCount = 0;
        if(count != null) {
            totalPageCount = count.intValue();
        }
        PageUtils<Doctor> doctorPage = new PageUtils<>(pageNo,pageSize,totalPageCount);
        List<Doctor> doctorList = doctorDao.findDoctorByPageWhere(findParam, doctorPage.getIndex(), doctorPage.getPageSize());

        if(doctorList.size() > 0) {
            for (Doctor doctor: doctorList) {
                Policlinic byId = policlinicDao.findById(doctor.getDepartment());
                doctor.setPoliclinic(byId);
            }
        }
        doctorPage.seteList(doctorList);
        return doctorPage;
    }

    @Override
    public Doctor queryById(int did) {
        Doctor doctor = doctorDao.findById(did);
        if(doctor != null) {
            Policlinic policlinic = policlinicDao.findById(doctor.getDepartment());
            doctor.setPoliclinic(policlinic);
            Education edu = policlinicDao.findEduById(doctor.getEducation());
            doctor.setEdu(edu);
        }
        return doctor;
    }

    @Override
    public boolean modifyDoctor(Doctor doctor) {
        return doctorDao.updateDoctor(doctor);
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        return doctorDao.addDoctor(doctor);
    }
}

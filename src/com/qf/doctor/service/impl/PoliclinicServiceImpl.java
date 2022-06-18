package com.qf.doctor.service.impl;

import com.qf.doctor.dao.PoliclinicDao;
import com.qf.doctor.dao.impl.PoliclinicDaoImpl;
import com.qf.doctor.pojo.Education;
import com.qf.doctor.pojo.Policlinic;
import com.qf.doctor.service.PoliclinicService;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 10:11
 */
public class PoliclinicServiceImpl implements PoliclinicService {

    private PoliclinicDao policlinicDao = new PoliclinicDaoImpl();

    @Override
    public List<Policlinic> queryAll() {
        return policlinicDao.findAll();
    }

    @Override
    public List<Education> queryEduAll() {
        return policlinicDao.findEduAll();
    }
}

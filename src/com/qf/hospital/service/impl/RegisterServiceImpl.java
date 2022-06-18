package com.qf.hospital.service.impl;

import com.qf.common.util.PageUtils;
import com.qf.hospital.dao.ChargeDao;
import com.qf.hospital.dao.RandCDao;
import com.qf.hospital.dao.RegisterDao;
import com.qf.hospital.dao.impl.ChargeDaoImpl;
import com.qf.hospital.dao.impl.RandCDaoImpl;
import com.qf.hospital.dao.impl.RegisterDaoImpl;
import com.qf.hospital.pojo.Charge;
import com.qf.hospital.pojo.ChargeAndRegister;
import com.qf.hospital.pojo.Register;
import com.qf.hospital.service.RegisterService;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 9:42
 */
public class RegisterServiceImpl implements RegisterService {

    private RegisterDao registerDao = new RegisterDaoImpl();
    private ChargeDao chargeDao = new ChargeDaoImpl();
    private RandCDao randCDao = new RandCDaoImpl();
    @Override
    public PageUtils<Register> queryByPage(String id, String name, Integer pageNo, Integer pageSize) {
        Long count = registerDao.countByPageWhere(id, name);
        int totalPageCount = 0;
        if(count != null) {
            totalPageCount = count.intValue();
        }
        PageUtils<Register> page = new PageUtils<>(pageNo, pageSize, totalPageCount);
        List<Register> registerList = registerDao.findRegisterByPageWhere(id, name, page.getIndex(), page.getPageSize());
        page.seteList(registerList);
        return page;
    }

    @Override
    public PageUtils<ChargeAndRegister> queryCRByPage(String rid,Integer pageNo, Integer pageSize){
        Long count = randCDao.countByPageWhere(rid);
        int totalPageCount = 0;
        if(count != null) {
            totalPageCount = count.intValue();
        }
        PageUtils<ChargeAndRegister> page = new PageUtils<>(pageNo, pageSize, totalPageCount);
        List<ChargeAndRegister> list = randCDao.findByPage(rid, page.getIndex(), page.getPageSize());
        for(ChargeAndRegister cr : list) {
            cr.setRegister(registerDao.findById(cr.getRid()));
            cr.setCharge(chargeDao.findByCid(cr.getCid()));
        }
        page.seteList(list);
        return page;

    }

    @Override
    public List<ChargeAndRegister> queryAll(String rid) {
        Long count = randCDao.countByPageWhere(rid);
        int totalPageCount = 0;
        if(count != null) {
            totalPageCount = count.intValue();
        }
        List<ChargeAndRegister> list = randCDao.findByPage(rid, 0, totalPageCount);
        for(ChargeAndRegister cr : list) {
            cr.setRegister(registerDao.findById(cr.getRid()));
            cr.setCharge(chargeDao.findByCid(cr.getCid()));
        }
        return list;
    }

    @Override
    public Double countMoney(String rid) {
        return randCDao.countMoney(rid);
    }

    @Override
    public List<Charge> queryByName(String name) {
        return chargeDao.findByName(name);
    }

    @Override
    public Charge queryByCid(Integer cid) {
        return chargeDao.findByCid(cid);
    }

    @Override
    public boolean saveCharge(String rid, int parseInt) {

        return randCDao.addCharge(rid,parseInt);
    }

}

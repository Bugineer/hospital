package com.qf.hospital.service;

import com.qf.common.util.PageUtils;
import com.qf.hospital.pojo.Charge;
import com.qf.hospital.pojo.ChargeAndRegister;
import com.qf.hospital.pojo.Register;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 9:16
 */
public interface RegisterService {

    PageUtils<Register> queryByPage(String id,String name,Integer pageNo,Integer pageSize);
    PageUtils<ChargeAndRegister> queryCRByPage(String rid, Integer pageNo, Integer pageSize);
    List<ChargeAndRegister> queryAll(String rid);
    Double countMoney(String rid);

    //输入模糊查询
    List<Charge> queryByName(String name);

    Charge queryByCid(Integer cid);

    //保存收费项
    boolean saveCharge(String rid, int parseInt);
}

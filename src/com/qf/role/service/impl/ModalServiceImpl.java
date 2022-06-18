package com.qf.role.service.impl;

import com.qf.role.dao.ModalDao;
import com.qf.role.dao.impl.ModalDaoImpl;
import com.qf.role.pojo.Modal;
import com.qf.role.service.ModalService;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 15:33
 */
public class ModalServiceImpl implements ModalService {
    private ModalDao modalDao = new ModalDaoImpl();
    @Override
    public List<Modal> queryAll() {
        return modalDao.findAll();
    }
}

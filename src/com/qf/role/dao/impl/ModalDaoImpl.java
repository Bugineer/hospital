package com.qf.role.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.role.dao.ModalDao;
import com.qf.role.pojo.Modal;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 15:28
 */
public class ModalDaoImpl implements ModalDao {
    @Override
    public List<Modal> findAll() {
        QueryRunner qr = JDBCUtils.JD.qr;
        String sql = "select * from modal where `status` = 1 order by id";
        List<Modal> modalList = null;
        try {
            modalList  = qr.query(sql, new BeanListHandler<>(Modal.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modalList;
    }

    @Override
    public List<Modal> findByRoleId(Integer roleId) {
        //先查中间表
        String sql = "select modalId from modal_role where roleId = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        List<Modal> modalList = null;
        try {
            List<Object> list = qr.query(sql, new ColumnListHandler(1), roleId);
            //遍历查出来的 list
            modalList = findByIds(list.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modalList;
    }

    @Override
    public List<Modal> findByIds(Object... modalID) {
        if (modalID == null || modalID.length == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder("select * from modal where id in (");
        for(int i = 0;i < modalID.length; i++) {
            sql.append(modalID[i]+",");
        }
        sql.replace(sql.length()-1,sql.length(),")");
        QueryRunner qr = JDBCUtils.JD.qr;
        List<Modal> modalList = null;
        try {
            modalList = qr.query(sql.toString(), new BeanListHandler<>(Modal.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(sql);
        return modalList;
    }

}

package com.qf.hospital.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.hospital.dao.ChargeDao;
import com.qf.hospital.dao.RegisterDao;
import com.qf.hospital.pojo.Charge;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 11:43
 */
public class ChargeDaoImpl implements ChargeDao {


    @Override
    public Charge findByCid(Integer cid) {
        String sql = "select * from charge where cid = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        Charge charge = null;
        try {
            charge = qr.query(sql, new BeanHandler<>(Charge.class), cid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return charge;
    }

    @Override
    public List<Charge> findByName(String name) {
        if(name == null || "".equals(name.trim())) {
            return null;
        }
        String sql = "select * from charge where cname like concat('%',?,'%')";
        QueryRunner qr = JDBCUtils.JD.qr;
        List<Charge> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(Charge.class), name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}

package com.qf.hospital.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.hospital.dao.RandCDao;
import com.qf.hospital.pojo.ChargeAndRegister;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 14:28
 */
public class RandCDaoImpl implements RandCDao {
    private QueryRunner qr = JDBCUtils.JD.qr;
    @Override
    public List<ChargeAndRegister> findByPage(String rid,Integer index,Integer pageSize) {
        String sql = "select * from charge_register where rid = ? order by createDate limit ?,?";
        List<ChargeAndRegister> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(ChargeAndRegister.class), rid, index, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Long countByPageWhere(String rid) {
        String sql = "select count(*) from charge_register where rid = ?";
        Long count = null;
        try {
            count  = (Long)qr.query(sql, new ScalarHandler(), rid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Double countMoney(String rid) {
        String sql = "select sum(chargeAmount) from charge_register c1 JOIN charge c2 on c1.cid = c2.cid where rid = ?";
        Double count = null;
        try {
            count  = (Double)qr.query(sql, new ScalarHandler(), rid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean addCharge(String rid, int cid) {
        String sql = "insert into charge_register(rid,cid,createDate) values(?,?,now())";
        int rows = 0;
        try {
            rows = qr.update(sql, rid, cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0 ? true:false;
    }
}

package com.qf.doctor.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.doctor.dao.PoliclinicDao;
import com.qf.doctor.pojo.Education;
import com.qf.doctor.pojo.Policlinic;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 8:36
 */
public class PoliclinicDaoImpl implements PoliclinicDao {
    QueryRunner qr = JDBCUtils.JD.qr;
    @Override
    public Policlinic findById(Integer id) {
        String sql = "select * from policlinic where pid = ?";
        Policlinic policlinic = null;
        try {
            policlinic = qr.query(sql,new BeanHandler<>(Policlinic.class),id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return policlinic;
    }

    @Override
    public List<Policlinic> findAll() {
        String sql = "select * from policlinic";
        List<Policlinic> policlinicList = null;
        try {
            policlinicList = qr.query(sql,new BeanListHandler<>(Policlinic.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return policlinicList;
    }

    @Override
    public Education findEduById(Integer id) {
        String sql = "select * from edu where eid = ?";
        Education education = queryOne(sql, Education.class, id);
        return education;
    }

    @Override
    public List<Education> findEduAll() {
        String sql = "select * from edu";
        List<Education> educationList = queryList(sql, Education.class);
        return educationList;
    }

    private <T> T queryOne(String sql,Class<T> clazz,Object...parm) {
        T t = null;
        try {
            t = qr.query(sql, new BeanHandler<>(clazz), parm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    private <T> List<T> queryList(String sql,Class<T> clazz,Object...parm) {
        List<T> tList = null;
        try {
            tList = qr.query(sql, new BeanListHandler<>(clazz), parm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tList;
    }
}

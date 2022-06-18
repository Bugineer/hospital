package com.qf.hospital.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.hospital.dao.RegisterDao;
import com.qf.hospital.pojo.Register;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-17 8:42
 */
public class RegisterDaoImpl implements RegisterDao {

    private QueryRunner qr = JDBCUtils.JD.qr;



    private void pageWhere(String id, String name, StringBuilder sql, List<Object> params) {
        //判断病历号是否为空
        if(id != null) {
            if(!"".equals(id.trim())) {
                id = id.trim();
                sql.append(" and rid = ? ");
                params.add(id);
            }
        }

        if(name != null) {
            if(!"".equals(name.trim())) {
                name = name.trim();
                sql.append(" and name like concat('%',?,'%') ");
                params.add(name);
            }
        }
    }

    @Override
    public List<Register> findRegisterByPageWhere(String id, String name, Integer index, Integer pageSize) {
        StringBuilder sql = new StringBuilder("select * from register where 1=1 ");
        List<Object> params = new ArrayList<>();
        pageWhere(id,name,sql,params);
        sql.append(" order by rid limit ?,? ");
        params.add(index);
        params.add(pageSize);
        List<Register> registerList = null;
        try {
            registerList = qr.query(sql.toString(), new BeanListHandler<>(Register.class), params.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return registerList;
    }

    @Override
    public Long countByPageWhere(String id, String name) {
        StringBuilder sql = new StringBuilder("select count(*) from register where 1=1 ");
        List<Object> params = new ArrayList<>();
        pageWhere(id,name,sql,params);
        Long count = null;
        try {
            count = (Long)qr.query(sql.toString(), new ScalarHandler(), params.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public Register findById(String id) {
        String sql = "select * from register where rid = ?";
        Register register = null;
        try {
            register = qr.query(sql, new BeanHandler<>(Register.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return register;
    }
}

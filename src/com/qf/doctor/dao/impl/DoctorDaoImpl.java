package com.qf.doctor.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.doctor.dao.DoctorDao;
import com.qf.doctor.param.FindDoctorParam;
import com.qf.doctor.pojo.Doctor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-16 8:36
 */
public class DoctorDaoImpl implements DoctorDao {
    @Override
    public List<Doctor> findDoctorByPageWhere(FindDoctorParam findParam, Integer index, Integer pageSize) {
        List<Doctor> doctorList = null;
        //查询参数
        List<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from doctor where 1=1");
        //拼接动态sql
        pageWhere(sql,findParam, param);
        sql.append(" order by did limit ?, ?");
        param.add(index);
        param.add(pageSize);
        QueryRunner qr = JDBCUtils.JD.qr;
        try {
            doctorList = qr.query(sql.toString(), new BeanListHandler<>(Doctor.class), param.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public Long countByPageWhere(FindDoctorParam findParm) {
        List<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select count(*) from doctor where 1=1");
        pageWhere(sql,findParm, param);
        Long count = null;
        QueryRunner qr = JDBCUtils.JD.qr;
        try {
            count  = (Long)qr.query(sql.toString(), new ScalarHandler(), param.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public Doctor findById(int did) {
        String sql = "select * from doctor where did = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        Doctor doctor = null;
        try {
            doctor = qr.query(sql, new BeanHandler<>(Doctor.class), did);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctor;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        String sql = "update doctor set phone=?, sex=?, birthday=?, email=?, department=?, education=?,remark=? where did = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        int rows = 0;
        try {
            rows = qr.update(sql,doctor.getPhone(),doctor.getSex(),doctor.getBirthday(),doctor.getEmail(),doctor.getDepartment(),doctor.getEducation(),doctor.getRemark(),doctor.getDid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows > 0 ? true:false;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        String sql = "insert into doctor(`name`,cardno,phone,sex,age,birthday,email,department,education,remark,hireDate) values(?,?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr = JDBCUtils.JD.qr;
        int rows = 0;
        try {
            rows = qr.update(sql, doctor.getName(), doctor.getCardno(), doctor.getPhone(), doctor.getSex(), doctor.getAge(), doctor.getBirthday(), doctor.getEmail(), doctor.getDepartment(), doctor.getEducation(), doctor.getRemark(),doctor.getHireDate());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows > 0 ? true:false;
    }

    /**
     * 拼接动态sql 方法
     * @param
     * @return
     */
    private void pageWhere(StringBuilder sql,FindDoctorParam findParam,List<Object> param) {
        //拼接多条件 动态sql
        //有值参数
        if(findParam.getDid() != null) { //先判断 是否为空
            if(!("".equals(findParam.getDid().trim()))) { //在判断 是否为 空串
                sql.append(" and did = ? ");
                param.add(Integer.parseInt(findParam.getDid().trim()));
            }
        }
        if(findParam.getName() != null) { //先判断 是否为空
            if(!("".equals(findParam.getName().trim()))) { //在判断 是否为 空串
                sql.append(" and name like concat('%',?,'%') ");
                param.add(findParam.getName().trim());
            }
        }
        if(findParam.getDepartment() != null) { //先判断 是否为空
            if(!("".equals(findParam.getDepartment().trim()))) { //在判断 是否为 空串
                sql.append(" and department = ? ");
                param.add(Integer.parseInt(findParam.getDepartment().trim()));
            }
        }
    }
}

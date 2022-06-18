package com.qf.role.dao.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.role.dao.RoleDao;
import com.qf.role.pojo.Role;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 10:42
 */
public class RoleDaoImpl implements RoleDao {



    @Override
    public List<Role> findRoleByPageWhere(String roleName, Integer index, Integer pageSize) {
        List<Role> roleList = null;
        //查询参数
        List<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from role where 1=1");
        //拼接动态sql
        pageWhere(sql,roleName, param);
        //service 层 一定要 传 非空 index 和 pageSize
        sql.append(" order by roleId limit ?, ?");
        param.add(index);
        param.add(pageSize);
        QueryRunner qr = JDBCUtils.JD.qr;
        try {
            roleList = qr.query(sql.toString(), new BeanListHandler<>(Role.class), param.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }


    //我的多条件只有一个条件
    @Override
    public Long countByPageWhere(String roleName) {
        List<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select count(*) from role where 1=1");
        pageWhere(sql,roleName, param);
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
    public int addRole(Role role) {
        String sql = "insert into role(roleName,status) values(?,?)";
        QueryRunner qr = JDBCUtils.JD.qr;
        int rows = 0;
        try {
            rows = qr.update(sql, role.getRoleName(), role.getStatus());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public Role findByRoleName(String roleName) {
        String sql = "select * from role where roleName = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        Role role = null;
        try {
            role = qr.query(sql, new BeanHandler<>(Role.class), roleName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public void addModal(Integer roleId, String[] modalIds) {
        if(modalIds == null || modalIds.length == 0) {
            return;
        }
        String sql = "insert into modal_role(roleId,modalId) values(?,?)";
        Object[][] params = new Object[modalIds.length][2];
        //批量添加
        for(int i = 0; i < modalIds.length; i++) {
            params[i][0] = roleId;
            params[i][1] = Integer.parseInt(modalIds[i]);
         }
        QueryRunner qr = JDBCUtils.JD.qr;
        try {
            qr.batch(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean delModal(Integer roleId) {
        String sql = "delete from modal_role where roleId = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        int rows = 0;
        try {
            rows = qr.update(sql, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delModal(String[] roleIds) {
        String sql = "delete from modal_role where roleId = ?";
        Object[][] params = new Object[roleIds.length][1];
        for(int i = 0; i < roleIds.length; i++) {
            params[i][0] = roleIds[i];
        }
        QueryRunner qr = JDBCUtils.JD.qr;
        try {
            qr.batch(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public boolean deleteByRoleId(Integer roleId) {
        String sql = "delete from role where roleId = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        int rows = 0;
        try {
            rows = qr.update(sql, roleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows > 0 ? true:false;
    }

    @Override
    public boolean deleteByRoleIds(String[] roleIds) {
        String sql = "delete from role where roleId = ?";
        Object[][] params = new Object[roleIds.length][1];
        for(int i = 0; i < roleIds.length; i++) {
            params[i][0] = roleIds[i];
        }
        QueryRunner qr = JDBCUtils.JD.qr;
        try {
            qr.batch(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public Role findByRoleId(Integer roleId) {
        String sql = "select * from role where roleId = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        Role role = null;
        try {
            role = qr.query(sql, new BeanHandler<>(Role.class), roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean updateRole(Role role) {
        String sql = "update role set roleName = ?, status = ? where roleId = ?";
        QueryRunner qr = JDBCUtils.JD.qr;
        int rows = 0;
        try {
            rows = qr.update(sql, role.getRoleName(), role.getStatus(), role.getRoleId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows > 0 ? true:false;
    }


    /**
     * 拼接动态sql 方法
     * @param roleName
     * @return
     */
    private void pageWhere(StringBuilder sql,String roleName,List<Object> param) {
        //拼接多条件 动态sql
        //有值参数
        if(roleName != null) { //先判断 是否为空
            if(!("".equals(roleName.trim()))) { //在判断 是否为 空串
                roleName = roleName.trim(); //去头尾空格
                sql.append(" and roleName like concat('%',?,'%') ");
                param.add(roleName);
            }
        }
    }

}

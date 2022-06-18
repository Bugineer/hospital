package com.qf.role.service.impl;

import com.qf.common.util.JDBCUtils;
import com.qf.common.util.PageUtils;
import com.qf.role.dao.ModalDao;
import com.qf.role.dao.RoleDao;
import com.qf.role.dao.impl.ModalDaoImpl;
import com.qf.role.dao.impl.RoleDaoImpl;
import com.qf.role.pojo.Modal;
import com.qf.role.pojo.Role;
import com.qf.role.service.RoleService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.SQLException;
import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 11:03
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao = new RoleDaoImpl();
    private ModalDao modalDao = new ModalDaoImpl();

    @Override
    public PageUtils<Role> queryRoleByPage(String roleName, Integer pageNo, Integer pageSize) {
        //创建一个PageUtils对象
        //设置当前页
        //设置一页显示多少条
        //设置 多条件查询的总记录数
        //先查询 总记录数
        Long count = roleDao.countByPageWhere(roleName);
        int totalPageCount = 0;
        if(count != null) {
            totalPageCount = count.intValue();
        }
        //设置 总页数 = 总记录数 / 每页显示多少条
        PageUtils<Role> rolePage = new PageUtils<>(pageNo,pageSize,totalPageCount);
        //设置 查询的集合
        //先查询 index = (pageNo - 1) * pageSize
        List<Role> roleList = roleDao.findRoleByPageWhere(roleName, rolePage.getIndex(), rolePage.getPageSize());
        rolePage.seteList(roleList);
        return rolePage;
    }

    @Override
    public boolean saveRole(Role role) {
        int rows = roleDao.addRole(role);
        return rows > 0 ? true: false;
    }

    @Override
    public Role queryByRoleName(String roleName) {
       return roleDao.findByRoleName(roleName);
    }

    @Override
    public void saveModal(Integer roleId, String[] modalIds) {
        roleDao.addModal(roleId,modalIds);
    }

    @Override
    public boolean clearModal(Integer roleId) {
        return roleDao.delModal(roleId);
    }

    @Override
    public boolean removeByRoleId(Integer roleId) {
        //先删除角色 再删 modal关系
        boolean flag = roleDao.deleteByRoleId(roleId);
        if(flag) {
            roleDao.delModal(roleId);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeByRoleIds(String[] roleIds) {
        //先删除角色 再删 modal关系
        boolean flag = roleDao.deleteByRoleIds(roleIds);
        if(flag) {
            roleDao.delModal(roleIds);
            return true;
        }
        return false;
    }


    @Override
    public Role queryByRoleId(Integer roleId) {
        Role role = roleDao.findByRoleId(roleId);
        List<Modal> modalList = modalDao.findByRoleId(roleId);
        if(role != null) {
            role.setModalList(modalList);
        }
        return role;
    }

    @Override
    public boolean modifyRole(Role role, String[] modalIds) {
        //先更新 role
        boolean flag = roleDao.updateRole(role);
        //再 根据 roleId 清空权限
        if(flag) {
            boolean delModal = roleDao.delModal(role.getRoleId());
            if(delModal) {
                //再赋权
                roleDao.addModal(role.getRoleId(),modalIds);
                return true;
            }
        }

        return false;
    }

}

package com.qf.role.dao;

import com.qf.role.pojo.Role;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 10:28
 */
public interface RoleDao {

    /**
     * 多条件分页查询
     * @param roleName
     * @param page
     * @return
     */
    List<Role> findRoleByPageWhere(String roleName, Integer index, Integer pageSize);

    /**
     * 查询 多条件 总记录数
     * @param roleName
     * @return
     */
    Long countByPageWhere(String roleName);

    int addRole(Role role);

    Role findByRoleName(String roleName);

    //赋权
    void addModal(Integer roleId, String[] modalIds);

    //根据 roleId 删除权限
    boolean delModal(Integer roleId);

    //批量删除 权限
    boolean delModal(String[] roleIds);

    //根据roleId 删除角色 以及角色的权限
    boolean deleteByRoleId(Integer roleId);

    //批量删除
    boolean deleteByRoleIds(String[] roleIds);

    //根据 roleId 查询 角色
    Role findByRoleId(Integer roleId);


    boolean updateRole(Role role);

}

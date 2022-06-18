package com.qf.role.service;

import com.qf.common.util.PageUtils;
import com.qf.role.pojo.Role;

/**
 * @author Salted Fish
 * @create 2022-06-14 11:01
 */
public interface RoleService {
    PageUtils<Role> queryRoleByPage(String roleName, Integer pageNo, Integer pageSize);

    boolean saveRole(Role role);

    Role queryByRoleName(String roleName);

    //赋权
    void saveModal(Integer roleId, String[] modalIds);

    //清空权限
    boolean clearModal(Integer roleId);

    //删除角色
    boolean removeByRoleId(Integer roleId);

    //批量删除
    boolean removeByRoleIds(String[] roleIds);

    //根据 roleId 查询 角色
    Role queryByRoleId(Integer roleId);


    boolean modifyRole(Role role,String[] modalIds);

}

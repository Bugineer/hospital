package com.qf.role.dao;

import com.qf.role.pojo.Modal;

import java.util.List;

/**
 * @author Salted Fish
 * @create 2022-06-14 15:27
 */
public interface ModalDao {

    //查询所有 可用modal
    List<Modal> findAll();
    //根据 roleId 查询 角色权限
    List<Modal> findByRoleId(Integer roleId);

    //根据 modelId list 查询 modal
    List<Modal> findByIds(Object...modalID);
}

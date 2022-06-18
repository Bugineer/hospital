package com.qf.role.pojo;

import java.util.List;

/**
 * 角色表
 * @author Salted Fish
 * @create 2022-06-14 10:29
 */
public class Role {
    private Integer roleId;
    private String roleName;
    private Integer status;

    private List<Modal> modalList;

    public Role() {
    }

    public Role(String roleName, Integer status) {
        this.roleName = roleName;
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Modal> getModalList() {
        return modalList;
    }

    public void setModalList(List<Modal> modalList) {
        this.modalList = modalList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", modalList=" + modalList +
                '}';
    }
}

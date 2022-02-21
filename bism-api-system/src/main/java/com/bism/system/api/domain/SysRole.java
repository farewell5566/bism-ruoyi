package com.bism.system.api.domain;

import com.bism.system.api.entity.BaseEntity;

public class SysRole extends BaseEntity {

    private Long roleId;

    private String roleName;

    //角色权限
    private String roleKey;

    //角色排序
    private String rokeSort;

    //数据范围
    private String dataScope;

    //角色状态
    private String status;

    //用户是否存在此角色标识、默认不存在
    private String delFlag;

    //菜单组
    private Long[] menuIds;

    //部门组（数据权限）
    private Long[] deptIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRokeSort() {
        return rokeSort;
    }

    public void setRokeSort(String rokeSort) {
        this.rokeSort = rokeSort;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }

    public Long[] getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds) {
        this.deptIds = deptIds;
    }
}

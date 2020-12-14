package com.li.service;

import com.li.domain.SysMenu;
import com.li.domain.SysRolePrivilege;
import com.baomidou.mybatisplus.extension.service.IService;
import com.li.model.RolePrivilegesParam;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface SysRolePrivilegeService extends IService<SysRolePrivilege> {

    /**
     * 查询角色的权限
     *
     * @param roleId
     * @return
     */
    List<SysMenu> findSysMenuAndPrivileges(Long roleId);


    /**
     * 给角色授权权限
     *
     * @param rolePrivilegesParam
     * @return
     */
    boolean grantPrivileges(RolePrivilegesParam rolePrivilegesParam);
}

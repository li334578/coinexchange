package com.li.service;

import com.li.domain.SysPrivilege;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface SysPrivilegeService extends IService<SysPrivilege> {


    /**
     * 获取该菜单下面所有的权限
     *
     * @param roleId roleId 代表当前的查询的角色的ID
     * @param menuId 菜单的ID
     * @return
     */
    List<SysPrivilege> getAllSysPrivilege(Long menuId, Long roleId);
}

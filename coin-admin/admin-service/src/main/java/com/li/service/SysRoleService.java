package com.li.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 判断一个用户是否为超级的管理员
     *
     * @param userId
     * @return
     */
    boolean isSuperAdmin(Long userId);

    /**
     * 使用角色的名称模糊分页角色查询
     * @param page
     * 分页数据
     * @param name
     *  角色的名称
     * @return
     */
    Page<SysRole> findByPage(Page<SysRole> page, String name);

}

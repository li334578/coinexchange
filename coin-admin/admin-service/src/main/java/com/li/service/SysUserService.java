package com.li.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 分页查询员工
     *
     * @param page     分页参数
     * @param mobile   员工的手机号
     * @param fullname 员工的全名称
     * @return
     */
    Page<SysUser> findByPage(Page<SysUser> page, String mobile, String fullname);


    /**
     * 新增员工
     *
     * @param sysUser
     * @return
     */
    boolean addUser(SysUser sysUser);

}

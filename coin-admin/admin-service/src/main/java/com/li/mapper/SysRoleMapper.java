package com.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.domain.SysRole;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取用户角色Code的实现
     *
     * @param userId
     * @return
     */
    String getUserRoleCode(Long userId);
}
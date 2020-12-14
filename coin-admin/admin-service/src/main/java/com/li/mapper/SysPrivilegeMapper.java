package com.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.domain.SysPrivilege;

import java.util.List;
import java.util.Set;

/**
  * @program : coinexchange
  * @author : LiWenBo
  * @description : ${description}
  * @date : 2020-12-13 15:08:03
  */
public interface SysPrivilegeMapper extends BaseMapper<SysPrivilege> {
  /**
   * 使用角色Id 查询权限
   * @param roleId
   * @return
   */
  List<SysPrivilege> selectByRoleId(Long roleId);

  /**
   * 使用角色的ID 查询权限的id
   * @param roleId
   * @return
   */
  Set<Long> getPrivilegesByRoleId(Long roleId);
}
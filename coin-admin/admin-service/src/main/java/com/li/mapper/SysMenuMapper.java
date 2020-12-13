package com.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.domain.SysMenu;

import java.util.List;

/**
  * @program : coinexchange
  * @author : LiWenBo
  * @description : ${description}
  * @date : 2020-12-13 15:08:03
  */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
  /**
   * 通过用户的id 查询用户的菜单数据
   * @param userId
   * @return
   */
  List<SysMenu> selectMenusByUserId(Long userId);
}
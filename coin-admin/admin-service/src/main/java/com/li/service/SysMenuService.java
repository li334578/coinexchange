package com.li.service;

import com.li.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : ${description}
 * @date : 2020-12-13 15:08:03
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 通过用户的id 查询用户的菜单数据
     *
     * @param userId
     * @return
     */
    List<SysMenu> getMenusByUserId(Long userId);
}

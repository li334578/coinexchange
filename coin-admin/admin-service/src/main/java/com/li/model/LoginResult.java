package com.li.model;

import com.li.domain.SysMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 登录结果
 * @date : 2020-12-13 16:05:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "登录结果")
public class LoginResult {
    /**
     * 登录产生的token
     */
    @ApiModelProperty(value = "登录产生的token")
    private String token;

    /**
     * 前端的菜单数据
     */
    @ApiModelProperty(value = "前端的菜单数据")
    private List<SysMenu> menus;

    /**
     * 权限数据
     */
    @ApiModelProperty(value = "权限数据")
    private List<SimpleGrantedAuthority> authorities;
}

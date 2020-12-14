package com.li.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-14 20:35:33
 */
@Data
@ApiModel(value = "接收角色和权限数据")
public class RolePrivilegesParam {

    @ApiModelProperty(value = "角色的ID")
    private Long roleId  ;

    @ApiModelProperty(value = "角色的包含的权限")
    private List<Long> privilegeIds =   Collections.emptyList();
}
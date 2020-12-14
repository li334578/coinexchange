package com.li.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 角色
 * @date : 2020-12-13 15:08:03
 */

@ApiModel(value = "com-li-domain-SysRole")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRole {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "名称")
    @NotNull
    private String name;

    /**
     * 代码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "代码")
    @NotNull
    private String code;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 创建人
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 修改人
     */
    @TableField(value = "modify_by",fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改人")
    private Long modifyBy;

    /**
     * 状态0:禁用 1:启用
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "状态0:禁用 1:启用")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "created",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date created;

    /**
     * 修改时间
     */
    @TableField(value = "last_update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date lastUpdateTime;
}
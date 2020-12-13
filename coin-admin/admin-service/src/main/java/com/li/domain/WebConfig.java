package com.li.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 网站配置信息
 * @date : 2020-12-13 15:08:03
 */

@ApiModel(value = "com-li-domain-WebConfig")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "web_config")
public class WebConfig {
    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "Id")
    private Long id;

    /**
     * 分组, LINK_BANNER ,WEB_BANNER
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "分组, LINK_BANNER ,WEB_BANNER")
    private String type;

    /**
     * 名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 值
     */
    @TableField(value = "value")
    @ApiModelProperty(value = "值")
    private String value;

    /**
     * 权重
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "权重")
    private Short sort;

    /**
     * 创建时间
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建时间")
    private Date created;

    /**
     * 超链接地址
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "超链接地址")
    private String url;

    /**
     * 是否使用 0 否 1是
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "是否使用 0 否 1是")
    private Boolean status;
}
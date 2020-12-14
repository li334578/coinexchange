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
 * @description : 系统资讯公告信息
 * @date : 2020-12-13 15:08:03
 */

@ApiModel(value = "com-li-domain-Notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "notice")
public class Notice {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 简介
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "简介")
    private String description;

    /**
     * 作者
     */
    @TableField(value = "author")
    @ApiModelProperty(value = "作者")
    private String author;

    /**
     * 文章状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "文章状态")
    private Integer status;

    /**
     * 文章排序，越大越靠前
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "文章排序，越大越靠前")
    private Integer sort;

    /**
     * 内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 最后修改时间
     */
    @TableField(value = "last_update_time")
    @ApiModelProperty(value = "最后修改时间")
    private Date lastUpdateTime;

    /**
     * 创建日期
     */
    @TableField(value = "created")
    @ApiModelProperty(value = "创建日期")
    private Date created;
}
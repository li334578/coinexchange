package com.li.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.domain.SysPrivilege;
import com.li.model.R;
import com.li.service.SysPrivilegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : * 权限管理的控制器
 * * 1 查询
 * * 2 修改
 * * 3 新增
 * * 4 删除
 * * 权限管理的权限：
 * * 查询：sys_privilege_query
 * * 修改：sys_privilege_update
 * * 新增：sys_privilege_create
 * * 删除：sys_privilege_delete
 * @date : 2020-12-13 16:51:24
 */
@RestController
@RequestMapping("/privileges")
@Api(tags = "权限的管理")
public class SysPrivilegeController {


    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    /**
     * 权限数据的分页查询
     *
     * @param page
     * @return
     */
    @GetMapping
    @ApiOperation(value = "分页查询权限数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页显示的大小"),
    })
    @PreAuthorize("hasAuthority('sys_privilege_query')")
    public R<Page<SysPrivilege>> findByPage(@ApiIgnore Page<SysPrivilege> page) {

        // 查询时，我们将最近新增的、修改的数据优先展示-> 排序->lastUpdateTime
        page.addOrder(OrderItem.desc("last_update_time"));
        Page<SysPrivilege> sysPrivilegePage = sysPrivilegeService.page(page);
        return R.ok(sysPrivilegePage);
    }


    @PostMapping
    @ApiOperation(value = "新增一个权限")
    @PreAuthorize("hasAuthority('sys_privilege_create')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPrivilege", value = "sysPrivilege 的json数据")
    })
    public R add(@RequestBody @Validated SysPrivilege sysPrivilege) {
        // 新增时，我们需要给我们的新增对象填充一些属性
        boolean save = sysPrivilegeService.save(sysPrivilege);
        if (save) {
            return R.ok("新增成功");
        }
        return R.fail("新增失败");
    }


    @PatchMapping
    @ApiOperation(value = "修改一个权限")
    @PreAuthorize("hasAuthority('sys_privilege_update')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPrivilege", value = "sysPrivilege 的json数据")
    })
    public R update(@RequestBody @Validated SysPrivilege sysPrivilege) {
        // 新增时，我们需要给我们的新增对象填充一些属性

        boolean save = sysPrivilegeService.updateById(sysPrivilege);
        if (save) {
            return R.ok("修改成功");
        }
        return R.fail("修改失败");
    }

}

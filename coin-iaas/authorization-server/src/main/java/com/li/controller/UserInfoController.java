package com.li.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-11 20:43:47
 */
@RestController
public class UserInfoController {

    /**
     * 获取当前登录的用户对象
     *
     * @param principal
     * @return
     */
    @GetMapping("/user/info")
    public Principal userInfo(Principal principal) {
        // 使用ThreadLocal来实现的
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return principal;
    }
}

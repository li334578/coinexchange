package com.li.service;

import com.li.model.LoginResult;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 登录的接口
 * @date : 2020-12-13 16:09:01
 */
public interface SysLoginService {
    /**
     * 登录的实现
     * @param username
     *  用户名
     * @param password
     *
     * 用户的密码
     * @return
     * 登录的结果
     */
    LoginResult login(String username , String password) ;
}

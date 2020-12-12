package com.li.service;

import com.li.model.WebLog;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-12 21:18:48
 */
public interface  TestService {
    /**
     * 通过username 查询weblog
     *
     */
    WebLog get(String username) ;
}

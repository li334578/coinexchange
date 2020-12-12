package com.li.service.impl;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.li.model.WebLog;
import com.li.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-12 21:19:09
 */
@Service
public class TestServiceImpl implements TestService {
    /**
     * 通过username 查询webLog
     *
     * @param username
     * @return
     */
    @Cached(name = "com.li.service.impl.TestServiceImpl:", key = "#username", cacheType = CacheType.BOTH)
    public WebLog get(String username) {
        WebLog webLog = new WebLog();
        webLog.setUsername(username);
        webLog.setResult("ok");
        return webLog;
    }
}

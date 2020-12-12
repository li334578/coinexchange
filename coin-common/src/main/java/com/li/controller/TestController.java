package com.li.controller;

import com.li.model.R;
import com.li.model.WebLog;
import com.li.service.TestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-12 20:42:29
 */
@RestController
@Api(tags = "CoinCommon中测试的接口")
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TestService testService;

    @GetMapping("/common/test")
    @ApiOperation(value = "测试方法", authorizations = {@Authorization("Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param1", value = "参数1", dataType = "String", paramType = "query", example = "paramValue"),
            @ApiImplicitParam(name = "param2", value = "参数2", dataType = "String", paramType = "query", example = "paramValue")
    })
    public R<String> testMethod(String param1, String param2) {
        return R.ok("ok");
    }

    @GetMapping("/common/date")
    @ApiOperation(value = "日志格式化测试", authorizations = {@Authorization("Authorization")})
    public R<Date> testMethod() {
        return R.ok(new Date());
    }

    @GetMapping("/redis/test")
    @ApiOperation(value = "redis测试", authorizations = {@Authorization("Authorization")})
    public R<String> testRedis() {
        WebLog webLog = new WebLog();
        webLog.setResult("ok");
        webLog.setMethod("com.li.domain.WebLog.testRedis");
        webLog.setUsername("1110");
        redisTemplate.opsForValue().set("com.li.domain.WebLog", webLog);
        return R.ok("OK"); //
    }


    @GetMapping("/jetcache/test")
    @ApiOperation(value = "jetcache缓存的测试", authorizations = {@Authorization("Authorization")})
    public R<String> testJetCache(String username) {
        WebLog webLog = testService.get(username);
        System.out.println(webLog);
        return R.ok("OK");
    }


}

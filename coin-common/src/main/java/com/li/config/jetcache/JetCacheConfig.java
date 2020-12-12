package com.li.config.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : JetCache配置类
 * @date : 2020-12-12 16:25:50
 */
@Configuration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.li.service.impl")
public class JetCacheConfig {

}

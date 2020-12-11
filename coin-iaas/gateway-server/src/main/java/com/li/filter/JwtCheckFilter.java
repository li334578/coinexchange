package com.li.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Set;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-11 21:08:15
 */
@Component
public class JwtCheckFilter implements GlobalFilter, Ordered {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${no.token.access.urls:/oauth/token,/admin/login,/admin/validate/code}")
    private Set<String> noTokenAccessUrls;

    /**
     * 过滤器拦截到用户请求后做什么
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1:该接口是否需要token才能访问
        if (allowNoTokenAccess(exchange)) {
            return chain.filter(exchange);
        }
        // 2:取出token
        // 获取用户的token
        String token = getToken(exchange);
        // 3:判断token是否有效
        if (StringUtils.isEmpty(token)) { // token 为 Empty
            return buildUNAuthorizedResult(exchange);
        }
        Boolean hasKey = stringRedisTemplate.hasKey(token);
        if (hasKey != null && hasKey) {
            return chain.filter(exchange);
        }
        return buildUNAuthorizedResult(exchange);

    }

    /**
     * 给用户响应一个没有哦token的错误
     *
     * @param exchange
     * @return
     */
    private Mono<Void> buildUNAuthorizedResult(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", "unauthorized");
        jsonObject.put("error_description", "invalid_token");
        DataBuffer dataBuffer = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return response.writeWith(Flux.just(dataBuffer));
    }

    /**
     * 从头里面获取
     *
     * @param exchange
     * @return
     */
    private String getToken(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorization) || authorization.trim().isEmpty()) {
            return null;
        }
        return authorization.replace("bearer ", "");
    }

    private boolean allowNoTokenAccess(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        if (noTokenAccessUrls.contains(path)) {
            return true;
        }
        return false;
    }

    /**
     * 拦截器顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

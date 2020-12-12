package com.li.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 授权服务器配置类
 * @date : 2020-12-11 20:02:43
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userServiceDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 添加第三方客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("coin-api") // 第三方客户端
                .secret(passwordEncoder.encode("coin-secret")) // 第三方客户端秘钥
                .scopes("all") // 第三方客户端的授权范围
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(7 * 24 * 3600) // token的有效期
                .refreshTokenValiditySeconds(30 * 24 * 3600)
                .and()
                .withClient("inside-app")
                .secret(passwordEncoder.encode("inside-secret"))
                .authorizedGrantTypes("client_credentials")
                .scopes("all")
                .accessTokenValiditySeconds(7 * 24 * 3600); // refresh_token的有效期
        super.configure(clients);
    }

    /**
     * 配置验证管理器
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
//                .tokenStore(redisTokenStore());
                .tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtAccessTokenConverter());
        super.configure(endpoints);
    }

    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 加载私钥
        ClassPathResource classPathResource = new ClassPathResource("coinexchange.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "coinexchange".toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("coinexchange", "coinexchange".toCharArray()));
        return jwtAccessTokenConverter;
    }

    /**
     * redis token store
     */
//    public TokenStore redisTokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }
}

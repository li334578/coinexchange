package com.li.service.impl;

import com.li.constant.LoginConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.li.constant.LoginConstant.*;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-11 21:26:24
 */
@Service
public class UserServiceDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String loginType = requestAttributes.getRequest().getParameter("login_type");
        if (StringUtils.isEmpty(loginType)) {
            throw new AuthenticationServiceException("请添加login_type参数");
        }
        UserDetails userDetails = null;
        try {
            String grant_type = requestAttributes.getRequest().getParameter("grant_type");
            if (LoginConstant.REFRESH_TOKEN.equals(grant_type.toUpperCase())) {
                username = adjustUsername(username, loginType);
            }
            switch (loginType) {
                case LoginConstant.ADMIN_TYPE: // 管理员登录
                    userDetails = loadAdminUserByUsername(username);
                    break;
                case LoginConstant.MEMBER_TYPE: // 会员登录
                    userDetails = loadMemberUserByUsername(username);
                    break;
                default:
                    throw new AuthenticationServiceException("暂不支持的登录方式" + loginType);
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            // 用户名不存在
            throw new AuthenticationServiceException("用户名不存在");
        }
        return userDetails;
    }

    /**
     * 纠正用户的名称
     *
     * @param username  用户的id -> username
     * @param loginType admin_type memeber_type
     * @return
     */
    private String adjustUsername(String username, String loginType) {
        if (LoginConstant.ADMIN_TYPE.equals(loginType)) {
            return jdbcTemplate.queryForObject(LoginConstant.QUERY_ADMIN_USER_WITH_ID, String.class, username);
        }
        if (LoginConstant.MEMBER_TYPE.equals(loginType)) {
            return jdbcTemplate.queryForObject(LoginConstant.QUERY_MEMBER_USER_WITH_ID, String.class, username);
        }
        return username;
    }

    /**
     * 普通用户登录
     *
     * @param username
     * @return
     */
    private UserDetails loadMemberUserByUsername(String username) {
        return jdbcTemplate.queryForObject(QUERY_MEMBER_SQL, new RowMapper<UserDetails>() {
            @Override
            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.wasNull()) {
                    throw new UsernameNotFoundException("会员：" + username + "不存在");
                }
                long id = rs.getLong("id"); // 获取用户的id
                String password = rs.getString("password");
                int status = rs.getInt("status");
                return new User(
                        String.valueOf(id),
                        password,
                        status == 1,
                        true,
                        true,
                        true,
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
                );
            }
        }, username, username);
    }

    /**
     * 后台管理人员登录
     *
     * @param username
     * @return
     */
    private UserDetails loadAdminUserByUsername(String username) {
        return jdbcTemplate.queryForObject(QUERY_ADMIN_SQL, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.wasNull()) {
                    throw new UsernameNotFoundException("用户：" + username + "不存在");
                }
                Long id = rs.getLong("id");
                String password = rs.getString("password");
                int status = rs.getInt("status");
                User user = new User(
                        String.valueOf(id), // 使用用户的id 代替用户的名称，这样会使得后面的很多情况得以处理
                        password,
                        status == 1,
                        true,
                        true,
                        true,
                        getUserPermissions(id));
                return user;
            }

            /**
             * 通过用户的id查询用户的权限数据
             * @param id
             * @return
             */
            private Collection<? extends GrantedAuthority> getUserPermissions(Long id) {
                // 查询用户是否为管理员
                String code = jdbcTemplate.queryForObject(QUERY_ROLE_CODE_SQL, String.class, id);
                List<String> permissions = null;
                if (ADMIN_CODE.equals(code)) { // 管理员
                    permissions = jdbcTemplate.queryForList(QUERY_ALL_PERMISSIONS, String.class);
                } else {
                    permissions = jdbcTemplate.queryForList(QUERY_PERMISSION_SQL, String.class, id);
                }
                if (permissions == null || permissions.isEmpty()) {
                    return Collections.EMPTY_SET;
                }
                return permissions
                        .stream()
                        .distinct() // 去重
                        .map(
                                SimpleGrantedAuthority::new // perm - >security可以识别的权限
                        )
                        .collect(Collectors.toSet());
            }
        }, username);
    }
}

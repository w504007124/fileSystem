package com.wh.file.service.impl;

import com.wh.file.domain.LoginUser;
import com.wh.file.domain.SysUser;
import com.wh.file.service.LoginService;
import com.wh.file.utils.JwtUtil;
import com.wh.file.utils.RedisCache;
import com.wh.file.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 14:54 2022/5/18
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser sysUser) {
        // 获取 AuthenticationManager authenticate进行用户认证 (在SecurityConfig.java中注入@Bean以及方法)
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword()); // 将用户登录名密码封装成UsernamePasswordAuthenticationToken对象

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);// 进行认证操作，不通过则为Null
        // 如果认证没用通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败"); // ，认证不通过
        }
        // 如果认证通过了，使用userid生成一个jwt   jwt存入ResponseResult进行返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        if (loginUser.getSysUser().getStatus().equals("1")) {
            throw new RuntimeException("该账号已停用"); // ，认证不通过
        }
        if (loginUser.getSysUser().getDelFlag() == 1) {
            throw new RuntimeException("该账号已被删除"); // ，认证不通过
        }
        String userid = loginUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 把完整的用户信息存入redis   userid作为key
        redisCache.setCacheObject("login:"+userid, loginUser);
        log.info("token:", jwt);
        return ResponseResult.success(map);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的用户id    JwtAuthenticationTokenFilter.java中
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getSysUser().getId();

        // 删除redis中的值
        redisCache.deleteObject("login:"+userid);

        return ResponseResult.success("注销成功");
    }
}

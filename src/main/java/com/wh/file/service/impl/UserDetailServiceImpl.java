package com.wh.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wh.file.domain.LoginUser;
import com.wh.file.domain.SysUser;
import com.wh.file.mapper.SysMenuMapper;
import com.wh.file.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 自定义登录用户认证
 *
 * @author WuHao on 17:00 2022/5/17
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        // 如果没用查询到用户则抛出异常
        if (Objects.isNull(sysUser)){ // 判断对象可以用Objects的一个方法
            throw new RuntimeException("用户名或者密码错误");
        }

        // 查询对应权限信息-跟授权有关，添加到LoginUser中
//        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = sysMenuMapper.selectPermsByUserId(sysUser.getId());
        // 把数据封装成UserDetails返回
        return new LoginUser(sysUser, list);
    }
}

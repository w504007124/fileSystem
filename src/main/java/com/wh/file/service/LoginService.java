package com.wh.file.service;

import com.wh.file.domain.SysUser;
import com.wh.file.utils.ResponseResult;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 14:54 2022/5/18
 */
public interface LoginService {

    /**
     * 登录
     * @param sysUser
     * @return
     */
    ResponseResult login(SysUser sysUser);

    /**
     * 退出登录
     * @return
     */
    ResponseResult logout();
}

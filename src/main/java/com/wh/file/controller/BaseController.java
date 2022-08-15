package com.wh.file.controller;

import com.wh.file.domain.LoginUser;
import com.wh.file.utils.SecurityUtils;
import com.wh.file.utils.StringUtils;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController
{

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public LoginUser getLoginUser()
    {
        return SecurityUtils.getLoginUser();
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return getLoginUser().getUserId();
    }

    /**
     * 获取登录部门id
     */
    public Long getDeptId()
    {
        return getLoginUser().getDeptId();
    }

    /**
     * 获取登录用户名
     */
    public String getUsername()
    {
        return getLoginUser().getUsername();
    }

}

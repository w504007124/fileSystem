package com.wh.file.service;

import com.wh.file.domain.SysUser;
import com.wh.file.domain.vo.SysUserAddReq;
import com.wh.file.utils.ResponseResult;

/**
 * 用户
 *
 * @author WuHao on 16:32 2022/6/13
 */
public interface ISysUserService {

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public String checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUserAddReq user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);


    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public ResponseResult insertUser(SysUserAddReq user);
}

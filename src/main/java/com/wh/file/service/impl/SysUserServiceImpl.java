package com.wh.file.service.impl;

import com.wh.file.domain.SysUser;
import com.wh.file.domain.SysUserRole;
import com.wh.file.domain.vo.SysUserAddReq;
import com.wh.file.mapper.SysUserMapper;
import com.wh.file.mapper.SysUserRoleMapper;
import com.wh.file.service.ISysUserService;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.StringUtils;
import com.wh.file.utils.constant.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:32 2022/6/13
 */
@Slf4j
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPhoneUnique(SysUserAddReq user) {


        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info))
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkEmailUnique(SysUser user) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult insertUser(SysUserAddReq user) {

        if (StringUtils.isNull(user.getUserName())) {
            throw new RuntimeException("用户名不能为空");
        }

        if (StringUtils.isNull(user.getNickName())) {
            throw new RuntimeException("昵称不能为空");
        }

        if (StringUtils.isNull(user.getPassword())) {
            throw new RuntimeException("密码不能为空");
        }
        SysUser sysUser = new SysUser();

        sysUser.setUserName(user.getUserName());
        sysUser.setNickName(user.getNickName());
        sysUser.setStatus("0");
        sysUser.setUserType("1");
        sysUser.setPassword(user.getPassword());
        sysUser.setDelFlag(0);
        // 新增用户信息
//        int rows = userMapper.insertUser(sysUser);
        userMapper.insert(sysUser);
        // 新增角色菜单权限
//        SysRoleMenu srm = new SysRoleMenu();
//        srm.setRoleId(100L);
//        srm.setMenuId(3L);
//        sysRoleMenuMapper.insert(srm);

        // 新增用户与角色管理
        SysUserRole sur = new SysUserRole();
        sur.setUserId(sysUser.getId());
        sur.setRoleId(100L);
        sysUserRoleMapper.insert(sur);
        return ResponseResult.success();
    }

}

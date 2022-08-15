package com.wh.file.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wh.file.domain.LoginUser;
import com.wh.file.domain.SysUser;
import com.wh.file.domain.vo.SysUserAddReq;
import com.wh.file.service.ISysUserService;
import com.wh.file.service.LoginService;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.SecurityUtils;
import com.wh.file.utils.annotation.SysLogs;
import com.wh.file.utils.constant.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 14:52 2022/5/18
 */
@RestController
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ISysUserService userService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser sysUser){
        // 登录
        return loginService.login(sysUser);
    }


    /**
     * 注册：
     *  1.一个手机号只能注册一个用户
     * @param user
     * @return
     */
    // 注册
    @SysLogs(title = "注册用户")
    @PostMapping("/user/register")
    public ResponseResult add(@Validated @RequestBody SysUserAddReq user)
    {

        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return ResponseResult.fail("注册用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return ResponseResult.fail("注册用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
//        else if (StringUtils.isNotEmpty(user.getEmail())
//                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
//        {
//            return ResponseResult.fail("注册用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return userService.insertUser(user);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }

//    @PostMapping("refresh")
//    public ResponseResult<?> refresh(HttpServletRequest request)
//    {
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser))
//        {
//            // 刷新令牌有效期
//            tokenService.refreshToken(loginUser);
//            return ResponseResult.success();
//        }
//        return ResponseResult.success();
//    }
}

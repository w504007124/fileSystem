package com.wh.file.expression;

import com.wh.file.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证
 *
 * @author WuHao on 14:17 2022/5/20
 */
@Component("er")
public class ExpressionRoot {

    public Boolean hasAuthority(String authority) {
        // 获取用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        // 判断用户是否存在authority中
        return permissions.contains(authority);
    }
}

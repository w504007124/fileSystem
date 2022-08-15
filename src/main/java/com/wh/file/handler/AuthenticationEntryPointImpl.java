package com.wh.file.handler;

import com.alibaba.fastjson.JSON;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证异常处理实现类
 *
 * @author WuHao on 16:45 2022/5/19
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请重新登录", null);
        //转换成json字符串
        String json = JSON.toJSONString(result);
        // 处理异常类
        WebUtils.renderString(response, json);
    }
}

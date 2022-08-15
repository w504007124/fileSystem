package com.wh.file.handler;

import com.alibaba.fastjson.JSON;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权异常处理类
 *
 * @author WuHao on 17:02 2022/5/19
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"授权失败，权限不足", null);
        //转换成json字符串
        String json = JSON.toJSONString(result);
        // 处理异常类
        WebUtils.renderString(response, json);
    }
}

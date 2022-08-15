package com.wh.file.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:31 2022/5/17
 */
public class WebUtils {
    /**
     * 讲字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

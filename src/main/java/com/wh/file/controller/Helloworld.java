package com.wh.file.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:57 2022/5/16
 */
@RestController
public class Helloworld {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:test:list')")
    @PreAuthorize("@er.hasAuthority('system:common')")
    public String hello() {
        return "hello world!";
    }
}

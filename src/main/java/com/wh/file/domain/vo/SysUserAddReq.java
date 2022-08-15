package com.wh.file.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 17:40 2022/6/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserAddReq {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为Null")
    @ApiModelProperty("用户名")
    private String userName;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为Null")
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为Null")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为Null")
    @ApiModelProperty("手机号")
    private String phonenumber;
}

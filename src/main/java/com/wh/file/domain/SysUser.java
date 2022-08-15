package com.wh.file.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2022-05-17 16:16:48
 */
@ApiModel(description = "用户表(SysUser)实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 432499587954857599L;
    /**
    * 主键
    */
    @TableId
    @ApiModelProperty("主键")
    private Long id;
    /**
    * 用户名
    */
    @ApiModelProperty("用户名")
    private String userName;
    /**
    * 昵称
    */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
    * 密码
    */
    @ApiModelProperty("密码")
    private String password;
    /**
    * 账号状态(0正常 1停用)
    */
    @ApiModelProperty("账号状态(0正常 1停用)")
    private String status;
    /**
    * 邮箱
    */
    @ApiModelProperty("邮箱")
    private String email;
    /**
    * 手机号
    */
    @ApiModelProperty("手机号")
    private String phonenumber;
    /**
    * 用户性别(0男 1女 2未知)
    */
    @ApiModelProperty("用户性别(0男 1女 2未知)")
    private String sex;
    /**
    * 头像
    */
    @ApiModelProperty("头像")
    private String avatar;
    /**
    * 用户类型(0管理员，1普通用户)
    */
    @ApiModelProperty("用户类型(0管理员，1普通用户)")
    private String userType;
    /**
    * 创建人
    */
    @ApiModelProperty("创建人")
    private Long createBy;
    /**
    * 修改人ID
    */
    @ApiModelProperty("修改人ID")
    private Long updateBy;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
    * 删除标志(0未删除 1删除)
    */
    @ApiModelProperty("删除标志(0未删除 1删除)")
    private Integer delFlag;
}

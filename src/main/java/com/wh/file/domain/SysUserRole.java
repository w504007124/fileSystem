package com.wh.file.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author ruoyi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 432499587954857599L;
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

}

package com.wh.file.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户和岗位关联 sys_user_post
 *
 * @author ruoyi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 432499587954857599L;
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

}

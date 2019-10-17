package com.tcrl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author taoxia
 * @since 2019-10-04
 */
@TableName("sys_role_permission")
@Data
public class RolePermission implements Serializable {

private static final long serialVersionUID=1L;

    private Integer roleid;

    private Integer permissionid;

}

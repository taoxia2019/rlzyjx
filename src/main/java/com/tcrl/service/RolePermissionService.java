package com.tcrl.service;

import com.tcrl.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-04
 */

public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     * @param roleid
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleid);

}

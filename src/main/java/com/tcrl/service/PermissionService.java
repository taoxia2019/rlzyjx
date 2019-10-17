package com.tcrl.service;

import com.alibaba.fastjson.JSONArray;
import com.tcrl.base.DataGridView;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-04
 */
public interface PermissionService extends IService<Permission> {

    Results<Permission> listAllPermission();

    DataGridView getMenuAll();

    Results<Permission> savePermission(Permission permission);

    Results<Permission> editPermission(Permission permission);

    Results<Permission> delectPermission(Integer id);


    Results getMenu(Integer userId);
}

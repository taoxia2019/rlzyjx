package com.tcrl.service;

import com.tcrl.base.result.Results;
import com.tcrl.dto.RoleDTO;
import com.tcrl.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
public interface RoleService extends IService<Role> {



    Results<Role> selectRoleAll();

    Results<Role> getAllRolesByPage(Integer offset, Integer limit);

    Results<Role> findByFuzzyRoleName(String rolename, Integer offset, Integer limit);



    int updateRole(RoleDTO roleDTO);

    Results<Role> saveRole(RoleDTO roleDTO);

    Results delete(Integer id);
}

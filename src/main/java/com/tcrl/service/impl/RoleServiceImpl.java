package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.base.result.Results;
import com.tcrl.dao.RolePermissionMapper;
import com.tcrl.dao.UserRoleMapper;
import com.tcrl.dto.RoleDTO;
import com.tcrl.entity.Role;
import com.tcrl.dao.RoleMapper;
import com.tcrl.entity.RolePermission;
import com.tcrl.entity.UserRole;
import com.tcrl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public Results<Role> selectRoleAll() {
        return Results.success(roleMapper.selectCount(null), roleMapper.selectList(null));
    }

    @Override
    public Results<Role> getAllRolesByPage(Integer offset, Integer limit) {
        return Results.success(roleMapper.countAllRoles().intValue(), roleMapper.getallRolesByPage(offset, limit));
    }

    @Override
    public Results<Role> findByFuzzyRoleName(String rolename, Integer offset, Integer limit) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.like("name", rolename);


        Page<Role> page = new Page<>(offset, limit);

        IPage<Role> rolesIPage = roleMapper.selectPage(page, roleQueryWrapper);
        Long total = rolesIPage.getTotal();
        List<Role> records = rolesIPage.getRecords();

        return Results.success(total.intValue(), records);

    }


    @Override
    public int updateRole(RoleDTO roleDTO) {
        List<Integer> permissionIds = roleDTO.getPermissionIds();
        //更新角色权限前要删除之前的全部权限
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleid", roleDTO.getId());
        rolePermissionMapper.delete(queryWrapper);
        //判断是否赋予了该角色权限，如果有就添加
        if (!CollectionUtils.isEmpty(permissionIds)) {
            saveRolePermission(roleDTO, permissionIds);
        }

        return roleMapper.update(roleDTO, new UpdateWrapper<Role>().eq("id", roleDTO.getId()));
    }

    private void saveRolePermission(RoleDTO roleDTO, List<Integer> permissionIds) {

        permissionIds.forEach(l -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleid(roleDTO.getId());
            rolePermission.setPermissionid(l);
            rolePermissionMapper.insert(rolePermission);
        });

    }

    @Override
    public Results<Role> saveRole(RoleDTO roleDTO) {
        // 1.save role
        roleMapper.insert(roleDTO);
        List<Integer> permissionIds = roleDTO.getPermissionIds();
        //2.save permission
        if (!CollectionUtils.isEmpty(permissionIds)) {
            saveRolePermission(roleDTO, permissionIds);
        }
        return Results.success();
    }

    @Override
    public Results delete(Integer id) {
        //删除role_permission中的数据
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleid", id);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(queryWrapper);
        if(rolePermissions.size()>0) {
            rolePermissionMapper.delete(queryWrapper);
        }
        //删除user_role中的数据
        QueryWrapper<UserRole> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("roleid", id);
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper1);
        if(userRoles.size()>0) {
            userRoleMapper.delete(queryWrapper1);
        }
        //删除role中的数据
        roleMapper.deleteById(id);
        return Results.success();
    }
}

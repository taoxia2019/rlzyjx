package com.tcrl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.DataGridView;
import com.tcrl.base.result.Results;
import com.tcrl.dao.RolePermissionMapper;
import com.tcrl.entity.Permission;
import com.tcrl.dao.PermissionMapper;
import com.tcrl.entity.RolePermission;
import com.tcrl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-04
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Results getMenu(Integer userId) {

        List<Permission> datas = permissionMapper.listByUserId(userId);
        datas=datas.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }
    @Override
    public Results<Permission> listAllPermission() {
        List<Permission> datas=permissionMapper.selectList(null);
        Integer integer = permissionMapper.selectCount(null);
        /*JSONArray array=new JSONArray();

        TreeUtils.setPermissionsTree(0,datas,array);*/


        return Results.success(integer,datas);
    }

    @Override
    public DataGridView getMenuAll() {

        return new DataGridView(permissionMapper.selectCount(null).longValue(),permissionMapper.selectList(null));
    }

    @Override
    public Results<Permission> savePermission(Permission permission) {
        int i = permissionMapper.insert(permission);
        return i>0?Results.success():Results.failure();
    }

    @Override
    public Results<Permission> editPermission(Permission permission) {
        int i = permissionMapper.updateById(permission);
        return i>0?Results.success():Results.failure();
    }

    @Override
    public Results<Permission> delectPermission(Integer id) {
        //删除当前ID的权限数据
        permissionMapper.deleteById(id);

        //删除role-permission表中ID为当前ID以及子ID的数据
        //1.查询出说有ID和子ID, 形成集合
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("parentid",id);
        List<Permission> permissions = permissionMapper.selectList(queryWrapper);
        List<Integer> permissionIds=new ArrayList<>();
        permissions.forEach(p->permissionIds.add(p.getId()));
        permissionIds.add(id);
        //2.删除关联表中ID为permissionid的数据
        QueryWrapper<RolePermission> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.in("permissionid",permissionIds);
        rolePermissionMapper.delete(queryWrapper1);

        //删除父ID为当前ID的权限数据
        QueryWrapper<Permission> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("parentid",id);
        permissionMapper.delete(queryWrapper2);

        return Results.success();
    }
}

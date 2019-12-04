package com.tcrl.service.impl;

import com.tcrl.entity.RolePermission;
import com.tcrl.dao.RolePermissionMapper;
import com.tcrl.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-04
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleid) {
        return rolePermissionMapper.queryRolePermissionIdsByRid(roleid);
    }
}

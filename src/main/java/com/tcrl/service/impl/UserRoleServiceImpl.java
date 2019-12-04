package com.tcrl.service.impl;

import com.tcrl.entity.UserRole;
import com.tcrl.dao.UserRoleMapper;
import com.tcrl.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

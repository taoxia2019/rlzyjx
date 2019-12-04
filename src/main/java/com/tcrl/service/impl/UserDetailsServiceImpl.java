package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.dao.PermissionMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.dto.LoginUser;
import com.tcrl.entity.Users;
import com.tcrl.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/10/9 16:32
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<Users> list = usersService.list(queryWrapper);
        Users users = list.get(0);
        if(users==null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }else if(users.getStatus()==Users.Status.DISABLED){
            throw new LockedException("用户被锁定，请联系管理员");
        }
        LoginUser loginUser=new LoginUser();
        BeanUtils.copyProperties(users,loginUser);
        loginUser.setPermissions(permissionMapper.listByUserId(users.getId()));

        return loginUser;
    }
}

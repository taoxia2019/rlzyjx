package com.tcrl.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.entity.Users;
import com.tcrl.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName GetSecurityUsername
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/10/23 15:39
 * @Version 1.0
 */
public class GetSecurityUsername {

    public static String getSecurityUsername(){
        //获取目前登录用户
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getUsername();

    }
}

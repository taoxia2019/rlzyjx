package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.entity.Users;
import com.tcrl.service.PerformanceResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
@Service
public class PerformanceResultServiceImpl extends ServiceImpl<PerformanceResultMapper, PerformanceResult> implements PerformanceResultService {
    @Autowired
    private PerformanceResultMapper performanceResultMapper;

    @Autowired
    private UsersService usersService;

    @Override
    public Results<PerformanceResult> getList() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = principal.getUsername();
        if(!username.equals("admin")) {
            QueryWrapper<Users> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("username", username);
            String dept = usersService.getOne(queryWrapper1).getDept();

            QueryWrapper<PerformanceResult> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("kaohedanwei", dept);
            return Results.success(performanceResultMapper.selectCount(queryWrapper2),performanceResultMapper.selectList(queryWrapper2));

        }else {
            return null;

        }
    }
}

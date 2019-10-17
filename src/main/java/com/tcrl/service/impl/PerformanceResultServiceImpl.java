package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.entity.Users;
import com.tcrl.service.PerformanceResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DataUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Results<PerformanceResult> getList(Integer offset, Integer limit) {
        //获取目前登录用户
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = principal.getUsername();
        //判断是否是管理员
        if(!username.equals("admin")) {
            //如果不是管理员，查找用户所在部门
            QueryWrapper<Users> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("username", username);
            String dept = usersService.getOne(queryWrapper1).getDept();

            //根据所在部门查询考核单位即数据（即填报单位）
            int count = performanceResultMapper.countAllPerformances(DataUtils.getMonth(), dept).intValue();
            //当前填报表考核月份
            List<PerformanceResult> performanceResults = performanceResultMapper.getallPerformancesByPage(offset, limit, DataUtils.getMonth(), dept);
            return Results.success(count,performanceResults);

        }else {
            //管理员返回全部当期考核表数据
            int count = performanceResultMapper.countAllPerformances(DataUtils.getMonth()).intValue();
            //当前填报表考核月份
            List<PerformanceResult> performanceResults = performanceResultMapper.getallPerformancesByPage(offset, limit, DataUtils.getMonth());
            return Results.success(count,performanceResults);

        }
    }
}

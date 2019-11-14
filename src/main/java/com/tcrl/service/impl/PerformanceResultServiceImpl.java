package com.tcrl.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.Results;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.exception.MyParseException;
import com.tcrl.service.PerformanceResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.JexlUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
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
    private PerformanceInitMapper performanceInitMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Results<PerformanceResult> getList(Integer offset, Integer limit) {
        //获取目前登录用户
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = principal.getUsername();

        //判断是否是管理员
        if (!username.equals("admin")) {
            //如果不是管理员，查找用户所在部门
            String dept = usersMapper.getUser(username).getDept();
            //根据所在部门查询考核单位即数据（即填报单位）
            int count = performanceResultMapper.countAllPerformances(DateUtils.getMonth(), dept).intValue();
            //当前填报表考核月份
            List<PerformanceResult> performanceResults = performanceResultMapper.getallPerformancesByPage(DateUtils.getMonth(), dept, offset, limit);
            return Results.success(count, performanceResults);

        } else {

            //管理员返回全部当期考核表数据
            int count = performanceResultMapper.countAllPerformances1(DateUtils.getMonth()).intValue();
            //当前填报表考核月份
            List<PerformanceResult> performanceResults = performanceResultMapper.getallPerformancesByPage1(DateUtils.getMonth(), offset, limit);
            return Results.success(count, performanceResults);
        }
    }

    @Override
    public Results saveResultfieldValue(Integer id, String field, String fieldValue) {
        PerformanceResult pr = performanceResultMapper.selectById(id);
        if ("mubiaozhi".equals(field)) {
            if (NumberUtils.isCreatable(fieldValue)) {
                pr.setMubiaozhi(Double.parseDouble(fieldValue));
                if (pr.getShijizhi() != null && pr.getCaozuofu() != null && pr.getMubiaozhi() != null) {
                    getKaohejieguo(pr);
                }
            } else {
                pr.setMubiaozhi(0.0);
                if (pr.getShijizhi() != null && pr.getCaozuofu() != null && pr.getMubiaozhi() != null) {
                    getKaohejieguo(pr);
                }
            }
        } else if ("shijizhi".equals(field)) {
            if (NumberUtils.isCreatable(fieldValue)) {
                pr.setShijizhi(Double.parseDouble(fieldValue));
                if (pr.getShijizhi() != null && pr.getCaozuofu() != null && pr.getMubiaozhi() != null) {
                    getKaohejieguo(pr);
                }
            } else {
                pr.setShijizhi(0.0);
                if (pr.getShijizhi() != null && pr.getCaozuofu() != null && pr.getMubiaozhi() != null) {
                    getKaohejieguo(pr);
                }
            }

        } else if ("caozuofu".equals(field)) {
            pr.setCaozuofu(fieldValue);
            if (pr.getShijizhi() != null && pr.getCaozuofu() != null && pr.getMubiaozhi() != null) {
                getKaohejieguo(pr);
            }
        } else if ("beizhu".equals(field)) {
            pr.setBeizhu(fieldValue);
        }
        int insert = performanceResultMapper.updateById(pr);
        if (insert > 0) {
            return Results.success();
        } else {
            return Results.failure();
        }


    }

    private void getKaohejieguo(PerformanceResult pr) {

        Double value = null;
        try {
            value = JexlUtils.getValue(pr.getShijizhi(), pr.getMubiaozhi(), pr.getCaozuofu());
            pr.setKaohejieguo(value);
        } catch (Exception e) {
            throw new MyParseException();
        }

    }


}

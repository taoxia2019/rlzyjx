package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.service.PerformanceInitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
@Service
public class PerformanceInitServiceImpl extends ServiceImpl<PerformanceInitMapper, PerformanceInit> implements PerformanceInitService {

    @Autowired
    private PerformanceInitMapper performanceInitMapper;
    @Override
    public Results<PerformanceInit> getAllPerformanceInitByPage(Integer offset, Integer limit) {
       return Results.success(performanceInitMapper.countAllPerformances().intValue(), performanceInitMapper.getallPerformancesByPage(offset, limit));
    }

    @Override
    public Results<PerformanceInit> savePerformanceInits(PerformanceInit performanceInit) {
        int i = performanceInitMapper.insert(performanceInit);
        if(i>0){
            return Results.success();
        } else {
            return Results.failure();
        }
    }

    @Override
    public Results<PerformanceInit> updatePerformanceInit(PerformanceInit performanceInit) {
        int i = performanceInitMapper.updateById(performanceInit);
        if(i>0){
            return Results.success();
        } else {
            return Results.failure();
        }
    }
}

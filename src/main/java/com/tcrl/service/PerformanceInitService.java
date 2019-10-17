package com.tcrl.service;

import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceInit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
public interface PerformanceInitService extends IService<PerformanceInit> {

    Results<PerformanceInit> getAllPerformanceInitByPage(Integer offset, Integer limit);

    Results<PerformanceInit> savePerformanceInits(PerformanceInit performanceInit);

    Results<PerformanceInit> updatePerformanceInit(PerformanceInit performanceInit);
}

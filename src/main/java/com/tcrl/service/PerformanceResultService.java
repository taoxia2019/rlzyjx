package com.tcrl.service;

import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
public interface PerformanceResultService extends IService<PerformanceResult> {


    Results<PerformanceResult> getList(Integer offset, Integer limit);
}

package com.tcrl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Chanliangguagou;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-11-14
 */
public interface ChanliangguagouService extends IService<Chanliangguagou> {

    Results<Chanliangguagou> getAllDeptGuagoujineByPage();
}

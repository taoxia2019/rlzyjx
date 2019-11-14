package com.tcrl.service;

import com.tcrl.base.result.Results;
import com.tcrl.entity.Deptemployee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-11-12
 */
public interface DeptemployeeService extends IService<Deptemployee> {

    Results<Deptemployee> getAllDeptemployeeByPage(Integer offset, Integer limit);


    Results<Deptemployee> updateDeptemployee(Deptemployee deptemployee);
}

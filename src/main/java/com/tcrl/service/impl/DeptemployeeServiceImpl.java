package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.base.result.Results;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.Deptemployee;
import com.tcrl.dao.DeptemployeeMapper;
import com.tcrl.entity.Users;
import com.tcrl.service.DeptemployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.utils.GetSecurityUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-11-12
 */
@Service
@Transactional
public class DeptemployeeServiceImpl extends ServiceImpl<DeptemployeeMapper, Deptemployee> implements DeptemployeeService {

    @Autowired
    DeptemployeeMapper deptemployeeMapper;
    @Autowired
    UsersMapper usersMapper;
    @Override
    public Results<Deptemployee> getAllDeptemployeeByPage(Integer offset, Integer limit) {
        //获取当前用户名
        String securityUsername = GetSecurityUsername.getSecurityUsername();
        if("admin".equals(securityUsername)) {
            //管理员，加载全部部门数据
            return Results.success(deptemployeeMapper.countAllDeptemployee().intValue(), deptemployeeMapper.getallDeptemployeeByPage(offset, limit));
        }else {
            //非管理员，加载对应部门数据
            String dept = usersMapper.getUser(securityUsername).getDept();
            return Results.success(deptemployeeMapper.countAllDeptemployeeByDept(dept).intValue(), deptemployeeMapper.getallDeptemployeeByDeptByPage(dept,offset, limit));
        }
    }

    @Override
    public Results<Deptemployee> updateDeptemployee(Deptemployee deptemployee) {
        int i = deptemployeeMapper.updateById(deptemployee);
        if(i>0){
            return Results.success();
        } else {
            return Results.failure();
        }
    }
}

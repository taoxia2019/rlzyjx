package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.Results;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.Chanliangguagou;
import com.tcrl.dao.ChanliangguagouMapper;
import com.tcrl.service.ChanliangguagouService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.utils.GetSecurityUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-11-28
 */
@Service
@Transactional
public class ChanliangguagouServiceImpl extends ServiceImpl<ChanliangguagouMapper, Chanliangguagou> implements ChanliangguagouService {

    @Autowired
    private ChanliangguagouMapper chanliangguagouMapper;

    @Autowired
    UsersMapper usersMapper;


    @Override
    public Results<Chanliangguagou> getAllDeptGuagoujineByPage() {


        //获取当前用户名
        String securityUsername = GetSecurityUsername.getSecurityUsername();
        if("admin".equals(securityUsername)) {
            //管理员，加载全部部门数据
            return Results.success(chanliangguagouMapper.selectCount(null), chanliangguagouMapper.selectList(null));
        }else {
            //非管理员，加载对应部门数据
            String dept = usersMapper.getUser(securityUsername).getDept();
            return Results.success(chanliangguagouMapper.selectCount(new QueryWrapper<Chanliangguagou>().eq("dept",dept)), chanliangguagouMapper.selectList(new QueryWrapper<Chanliangguagou>().eq("dept",dept)));
        }
    }

}

package com.tcrl.service.impl;

import java.util.Map;

import com.tcrl.base.result.Results;
import com.tcrl.base.result.ResponseCode;
import com.tcrl.dao.EmpChanliangguagouDao;
import com.tcrl.entity.EmpChanliangguagou;
import com.tcrl.service.EmpChanliangguagouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class  EmpChanliangguagouServiceImpl implements EmpChanliangguagouService {

    @Autowired
    private EmpChanliangguagouDao empChanliangguagouDao;

    @Override
    public Results<EmpChanliangguagou> getByPage(Integer offset, Integer limit) {
        return new Results(0,"",null,empChanliangguagouDao.count(), empChanliangguagouDao.list(offset, limit));

    }

    @Override
    public Results save(EmpChanliangguagou empChanliangguagou) {
        int res = 0;
        if(StringUtils.isEmpty(empChanliangguagou.getId())){
            res =  empChanliangguagouDao.save(empChanliangguagou);
        }else{
            res =  empChanliangguagouDao.update(empChanliangguagou);
        }
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public Results getById(Integer id) {
        EmpChanliangguagou res =  empChanliangguagouDao.getById(id);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    public EmpChanliangguagou getEmpChanliangguagouById(Integer id) {
        return empChanliangguagouDao.getById(id);
    }

    @Override
    @Transactional
    public Results update(EmpChanliangguagou empChanliangguagou) {
        int res =  empChanliangguagouDao.update(empChanliangguagou);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

    @Override
    @Transactional
    public Results delete(EmpChanliangguagou empChanliangguagou) {
        int res =  empChanliangguagouDao.delete(empChanliangguagou);
        Results results = new Results();
        results.setCode(ResponseCode.SUCCESS.getCode());
        results.setMsg(ResponseCode.SUCCESS.getMessage());
        return results;
    }

}

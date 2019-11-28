package com.tcrl.service;

import com.tcrl.dao.ChanliangguagouMapper;
import com.tcrl.dao.DeptemployeeMapper;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.entity.Deptemployee;
import com.tcrl.entity.Chanliangguagou;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ScheduledService
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/11/13 16:47
 * @Version 1.0
 */
@Service
public class ScheduledService {
    @Autowired
    private PerformanceResultMapper performanceResultMapper;
    @Autowired
    private PerformanceInitMapper performanceInitMapper;

    @Autowired
    private DeptemployeeMapper deptemployeeMapper;
    @Autowired
    private ChanliangguagouMapper chanliangguagouMapper;

    //初始化月度考核填报表
    @Scheduled(cron="0 0 0 1 * ?")
    public void test1(){
        //将所有数据存储到RESULT表
        List<PerformanceInit> performanceInits = performanceInitMapper.selectList(null);
        performanceInits.stream().forEach(perInit -> {
            PerformanceResult pResult = getPerformanceResult(perInit);
            performanceResultMapper.insert(pResult);
        });
    }

    //初始化月度产量挂钩表
    @Scheduled(cron="0 0 0 1 * ?")
    public void test2(){
        //将所有数据存储到RESULT表
        List<Deptemployee> deptemployees = deptemployeeMapper.selectList(null);
        deptemployees.stream().forEach(deptEmp -> {
            Chanliangguagou chanliangguagou = getChanliangguagou(deptEmp);
            chanliangguagouMapper.insert(chanliangguagou);
        });
    }

    //两个类对拷 PerformanceInit ->PerformanceResult
    private PerformanceResult getPerformanceResult(PerformanceInit perInit) {
        PerformanceResult pResult = new PerformanceResult();
        BeanUtils.copyProperties(perInit, pResult);
        pResult.setKaoheyuefen(DateUtils.getMonth());
        pResult.setKaohejieguo(0.0);
        pResult.setId(null);
        return pResult;
    }

    //两个类对拷 员工信息表与产量挂钩表
    private Chanliangguagou getChanliangguagou(Deptemployee deptEmp) {
        Chanliangguagou chanliangguagou = new Chanliangguagou();
        BeanUtils.copyProperties(deptEmp, chanliangguagou);
        chanliangguagou.setKaoheyuefen(DateUtils.getMonth());
        chanliangguagou.setId(null);
        return chanliangguagou;
    }

}

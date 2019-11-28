package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.Constast;
import com.tcrl.base.result.Results;
import com.tcrl.dao.ChanliangguagouMapper;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.Chanliangguagou;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.exception.MyParseException;
import com.tcrl.service.PerformanceResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.JexlUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ChanliangguagouMapper chanliangguagouMapper;

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

            //调用方法，生成部门员工产量挂钩金额以及部门挂钩总额
        //优化执行效率11.27
        if("scr生产计划完成率".equals(pr.getKaohexiangmu())||"细线生产计划完成率".equals(pr.getKaohexiangmu())||"并绞线生产计划完成率".equals(pr.getKaohexiangmu())){
            Double scrM;
            Double scrS;
            Double xiancM;
            Double xiancS;
            Double suzM;
            Double suzS;
            QueryWrapper<PerformanceResult> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            List<PerformanceResult> performanceResults = performanceResultMapper.selectList(queryWrapper);
            scrM = performanceResults.stream()
                    .filter(p -> "铸轧分厂".equals(p.getBeikaohedanwei()))
                    .map(p -> p.getMubiaozhi()).findAny().get();
            scrS = performanceResults.stream()
                    .filter(p -> "铸轧分厂".equals(p.getBeikaohedanwei()))
                    .map(p -> p.getShijizhi()).findAny().get();
            xiancM = performanceResults.stream()
                    .filter(p -> "线材分厂".equals(p.getBeikaohedanwei()))
                    .map(p -> p.getMubiaozhi()).findAny().get();
            xiancS = performanceResults.stream()
                    .filter(p -> "线材分厂".equals(p.getBeikaohedanwei()))
                    .map(p -> p.getShijizhi()).findAny().get();
            suzM = performanceResults.stream()
                    .filter(p -> "苏州分公司".equals(p.getBeikaohedanwei()))
                    .map(p -> p.getMubiaozhi()).findAny().get();
            suzS = performanceResults.stream()
                    .filter(p -> "苏州分公司".equals(p.getBeikaohedanwei()))
                    .map(p -> p.getShijizhi()).findAny().get();

            if (scrM != null && scrS != null && xiancM != null && xiancS != null && suzM != null && suzS != null) {
                fillChanliangguagou(scrM, scrS, xiancM, xiancS, suzM, suzS);
            }
    }

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
            value=Math.round(value*10)/10.0;
            pr.setKaohejieguo(value);
        } catch (Exception e) {
            throw new MyParseException();
        }

    }

    //设置部门产量挂钩个人数据以及部门金额总额。
    // 未考虑累计超产因素
    private void fillChanliangguagou(Double scrM,Double scrS,Double xiancM,Double xiancS,Double suzM,Double suzS){
        QueryWrapper<Chanliangguagou> chanliangguagouQueryWrapper = new QueryWrapper<>();
        chanliangguagouQueryWrapper.eq("kaoheyuefen",DateUtils.getMonth());

        List<Chanliangguagou> chanliangguagous = chanliangguagouMapper.selectList(chanliangguagouQueryWrapper);
        chanliangguagous.forEach(clgg->{
            if("铸轧分厂".equals(clgg.getGuagoudanwei())){
                Double value=clgg.getJixiaogongzi()* Constast.CHANLIANGGUAGOU_COEFFICIENT*(scrS/scrM-1);
                value=Math.round(value*10)/10.0;
                clgg.setGuagoujine(value);

                chanliangguagouMapper.updateById(clgg);
            }else if("线材分厂".equals(clgg.getGuagoudanwei())){
                Double value=clgg.getJixiaogongzi()* Constast.CHANLIANGGUAGOU_COEFFICIENT*(xiancS/xiancM-1);
                value=Math.round(value*10)/10.0;
                clgg.setGuagoujine(value);
                chanliangguagouMapper.updateById(clgg);
            }else if("铸轧分厂+线材分厂".equals(clgg.getGuagoudanwei())){
                Double guagouS=clgg.getJixiaogongzi()*Constast.CHANLIANGGUAGOU_COEFFICIENT*(scrS/scrM-1)/2;
                guagouS=Math.round(guagouS*10)/10.0;
                Double guagouX=clgg.getJixiaogongzi()*Constast.CHANLIANGGUAGOU_COEFFICIENT*((xiancS+suzS)/(xiancM+suzM)-1)/2;
                guagouX=Math.round(guagouX*10)/10.0;
                clgg.setGuagoujine(guagouS+guagouX);
                chanliangguagouMapper.updateById(clgg);
            }
            //调用方法，生成部门挂钩总额
            fillDeptChanliangguagou();
        });


    }

    //生成部门挂钩总额方法
    public void fillDeptChanliangguagou(){
        QueryWrapper<PerformanceResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kaoheyuefen",DateUtils.getMonth());
        performanceResultMapper.selectList(queryWrapper).forEach(p->{
            if("分厂生产计划完成率".equals(p.getKaohexiangmu())){
                Double deptGuagoujine=chanliangguagouMapper.sumGuagoujineByDept(p.getBeikaohedanwei(),DateUtils.getMonth());
                deptGuagoujine=Math.round(deptGuagoujine*10)/10.0;
                p.setKaohejieguo(deptGuagoujine);
                performanceResultMapper.updateById(p);
            }

        });
    }


}

package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.Results;
import com.tcrl.dao.PerformanceInitMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.dao.PerformanceResultMapper;
import com.tcrl.exception.MyParseException;
import com.tcrl.service.PerformanceResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.JexlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Results<PerformanceResult> getList(Integer offset, Integer limit) {
        //获取目前登录用户
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = principal.getUsername();
        System.out.println("当前用户："+username);
        //判断是否是管理员
        if (!username.equals("admin")) {
            //如果不是管理员，查找用户所在部门
            String dept = usersMapper.getUser(username).getDept();

            //根据考核月份和考核单位判断是否已经有结果集
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("kaohedanwei", dept);

            List<PerformanceResult> list1 = performanceResultMapper
                    .selectList(performanceResultQueryWrapper);
            if (list1.size() != 0) {
                //根据所在部门查询考核单位即数据（即填报单位）
                int count = performanceResultMapper.countAllPerformances(DateUtils.getMonth(), dept).intValue();
                //当前填报表考核月份
                List<PerformanceResult> performanceResults = performanceResultMapper.getallPerformancesByPage(DateUtils.getMonth(), dept, offset, limit);
                return Results.success(count, performanceResults);
            } else {
                QueryWrapper<PerformanceInit> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.eq("kaohedanwei", dept);
                List<PerformanceInit> PerformanceInitlist = performanceInitMapper.selectList(queryWrapper2);
                PerformanceInitlist.stream().forEach(perInit -> {
                    PerformanceResult pResult = getPerformanceResult(perInit);
                    performanceResultMapper.insert(pResult);
                });
                //根据所在部门查询考核单位即数据（即填报单位）
                int count = performanceResultMapper.countAllPerformances(DateUtils.getMonth(), dept).intValue();
                //当前填报表考核月份
                List<PerformanceResult> performanceResults = performanceResultMapper.getallPerformancesByPage(DateUtils.getMonth(), dept, offset, limit);
                return Results.success(count, performanceResults);
            }

        } else {
            //管理员身份
            //根据月份查询结果集是否有数据
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper1 = new QueryWrapper<>();
            performanceResultQueryWrapper1.eq("kaoheyuefen", DateUtils.getMonth());
            List<PerformanceResult> performanceResults1 = performanceResultMapper.selectList(performanceResultQueryWrapper1);

            if (performanceResults1.size() !=0) {
                //如果有数据，将没有初始化表中没有填入到结果集
                List<PerformanceInit> performanceInits = performanceInitMapper.selectList(null);
                //获取初始化表中status的集合
                List<Integer> initStatusCollect = performanceInits
                        .stream().map(perinit -> perinit.getStatus()).collect(Collectors.toList());
                //获取初结果表中status的集合
                List<Integer> resultStatusCollect = performanceResults1.stream()
                        .map(perresult -> perresult.getStatus()).collect(Collectors.toList());
                //去除结果集中有的status
                initStatusCollect.removeAll(resultStatusCollect);
                if(initStatusCollect.size()>0){
                    initStatusCollect.stream().forEach(i->{
                        performanceInits.stream().forEach(perInit->{
                               if( perInit.getStatus().equals(i)){
                                   PerformanceResult pResult = getPerformanceResult(perInit);
                                   performanceResultMapper.insert(pResult);
                               }
                        });
                    });
                }
            } else {
                //将所有数据存储到RESULT表
                List<PerformanceInit> performanceInits = performanceInitMapper.selectList(null);
                performanceInits.stream().forEach(perInit -> {
                    PerformanceResult pResult = getPerformanceResult(perInit);
                    performanceResultMapper.insert(pResult);
                });
            }
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
        if("mubiaozhi".equals(field)){
            pr.setMubiaozhi(Double.parseDouble(fieldValue));
            getKaohejieguo(pr);
        }else if("shijizhi".equals(field)){
            pr.setShijizhi(Double.parseDouble(fieldValue));
            getKaohejieguo(pr);

        }else if("caozuofu".equals(field)){
            pr.setCaozuofu(fieldValue);
            getKaohejieguo(pr);
        }else {
            pr.setBeizhu(fieldValue);

        }
        int insert = performanceResultMapper.updateById(pr);
        if(insert>0){
            return Results.success();
        }else {
            return Results.failure();
        }


    }

    private void getKaohejieguo(PerformanceResult pr) {
        if(pr.getShijizhi()!=null&& pr.getCaozuofu()!=null&&pr.getMubiaozhi()!=null){
            Double value=null;
            try {
                value= JexlUtils.getValue(pr.getShijizhi(),pr.getMubiaozhi(),pr.getCaozuofu());
                pr.setKaohejieguo(value);
            }catch (Exception e){
                throw new MyParseException();
            }
        }
    }


    //两个类对拷 PerformanceInit ->PerformanceResult
    private PerformanceResult getPerformanceResult(PerformanceInit perInit) {
        PerformanceResult pResult = new PerformanceResult();
        perInit.setKaoheyuefen(DateUtils.getMonth());
        perInit.setId(null);
        BeanUtils.copyProperties(perInit, pResult);
        return pResult;
    }


}

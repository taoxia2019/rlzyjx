package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.entity.Users;
import com.tcrl.service.PerformanceInitService;
import com.tcrl.service.PerformanceResultService;
import com.tcrl.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
@Controller
@RequestMapping("/performance")
public class PerformanceResultController {
    @Autowired
    private PerformanceResultService performanceResultService;

    @Autowired
    private PerformanceInitService performanceInitService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/performance-fill")
    public String getFillPage(){
        return "performance/performance-fill";
    }

    @RequestMapping("/fill")
    @ResponseBody
    public Results<PerformanceResult> getPerformanceResultList(){

        UserDetails principal = (UserDetails)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = principal.getUsername();
        if(!username.equals("admin")) {
            QueryWrapper<Users> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("username", username);
            String dept = usersService.getOne(queryWrapper1).getDept();

            QueryWrapper<PerformanceInit> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("kaohedanwei", dept);
            List<PerformanceInit> PerformanceInitlist = performanceInitService.list(queryWrapper2);
            PerformanceInitlist.stream().forEach(perInit->{
                PerformanceResult pResult=new PerformanceResult();
                perInit.setKaoheyuefen(getMonth());
                perInit.setId(null);
                BeanUtils.copyProperties(perInit,pResult);
                performanceResultService.save(pResult);
            });
        }else {
            List<PerformanceInit> list = performanceInitService.list();
            list.stream().forEach(perInit->{
                PerformanceResult pResult=new PerformanceResult();
                perInit.setKaoheyuefen(getMonth());
                perInit.setId(null);
                BeanUtils.copyProperties(perInit,pResult);
                performanceResultService.save(pResult);
            });
        }
        return performanceResultService.getList();
    }

    public String getMonth(){
        if(LocalDate.now().getDayOfMonth()>15) {
            return LocalDate.now().getMonthValue()+"月";
        }else {
            return LocalDate.now().getMonthValue()-1+"月";
        }
    }






    //kpi:performance:fill

}


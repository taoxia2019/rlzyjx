package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.entity.Users;
import com.tcrl.service.PerformanceInitService;
import com.tcrl.service.PerformanceResultService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DataUtils;
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
 * 前端控制器
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

    @ResponseBody
    @RequestMapping("editField")
    public Results getResultField(Integer id,String field,String fieldValue){

        return performanceResultService.saveResultfieldValue(id,field,fieldValue);
    }



    @RequestMapping("/performance-fill")
    public String getFillPage() {

        return "performance/performance-fill";
    }

    @RequestMapping("/fill")
    @ResponseBody
    public Results<PerformanceResult> getPerformanceResultList(PageTableRequest page) {
        page.countOffset();

        return performanceResultService.getList(page.getOffset(), page.getLimit());
    }




}


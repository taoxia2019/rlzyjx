package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;
import com.tcrl.entity.PerformanceResult;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.PerformanceResultService;
import com.tcrl.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private DepartmentService departmentService;
    @Autowired
    private PerformanceResultService performanceResultService;

    @ResponseBody
    @RequestMapping("editField")
    public Results getResultField(Integer id,String field,String fieldValue){
        System.out.println(id+"--"+field+"---"+fieldValue);
        return performanceResultService.saveResultfieldValue(id,field,fieldValue);

    }

    @RequestMapping("/performance-fill")
    public String getFillPage() {

        return "performance/performance-fill";
    }

   /* @RequestMapping("/detailedrule-fill")
    public String getFillPage2() {

        return "performance/detailedrule-fill";
    }*/

    @RequestMapping("/fill")
    @ResponseBody
    public Results<PerformanceResult> getPerformanceResultList(PageTableRequest page) {
        page.countOffset();
        return performanceResultService.getList(page.getOffset(), page.getLimit());
    }

    /*@RequestMapping("/performance-result")
    public String getResultPage(Model model) {
        model.addAttribute("departments",departmentService.list(new QueryWrapper<Department>().ne("id", 1)));
        System.out.println(departmentService.list().stream().count()+"---------");
        return "performance/performance-result";
    }*/

    @RequestMapping("/findByDeptResult")
    @ResponseBody
    public  Results findByDeptResult(String kaoheyuefen,String beikaohedanwei){
        System.out.println(kaoheyuefen+"-----"+beikaohedanwei);
        if(null==beikaohedanwei&&null==kaoheyuefen) {
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", "铸轧分厂");
            List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
            int count = performanceResultService.count(performanceResultQueryWrapper);
            return Results.success(count,performanceResults);
        }else if("".equals(kaoheyuefen)&&null!=beikaohedanwei){
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
            int count = performanceResultService.count(performanceResultQueryWrapper);
            return Results.success(count,performanceResults);

        } else {
            QueryWrapper<PerformanceResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", kaoheyuefen);
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<PerformanceResult> performanceResults = performanceResultService.list(performanceResultQueryWrapper);
            int count = performanceResultService.count(performanceResultQueryWrapper);
            return Results.success(count,performanceResults);
        }



    }






}


package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;
import com.tcrl.entity.PerformanceInit;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.PerformanceInitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
@RequestMapping("/performanceInit")
public class PerformanceInitController {

    @Autowired
    private PerformanceInitService performanceInitService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results<PerformanceInit> getPerformanceInit(PageTableRequest page){
        page.countOffset();
        return performanceInitService.getAllPerformanceInitByPage(page.getOffset(),page.getLimit());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public String addUser(Model model){
        model.addAttribute(new PerformanceInit());
        model.addAttribute("departments",departmentService.list(new QueryWrapper<Department>().ne("id", 1)));

        return "performance/performance-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results<PerformanceInit> saveUser(PerformanceInit performanceInit){

        return performanceInitService.savePerformanceInits(performanceInit);
    }

    //跳转编辑页面
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public String editUser(Model model,PerformanceInit performanceInit1){
        PerformanceInit p = performanceInitService.getById(performanceInit1.getId());
        //获取被考核部门start
        List<Department> depts1 = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept : depts1) {
            if(dept.getDeptName().equals(p.getBeikaohedanwei())){
                dept.setFlag(true);
                break;
            }
        }
        //获取部门end
        //获取考核部门start
        List<Department> depts2 = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept1 : depts2) {
            if(dept1.getDeptName().equals(p.getKaohedanwei())){
                dept1.setFlag(true);
                break;
            }
        }
        //获取部门end
        model.addAttribute("performanceInit",p);
        model.addAttribute("departments1",depts1);
        model.addAttribute("departments2",depts2);
        return "performance/performance-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results<PerformanceInit> updateUser(PerformanceInit performanceInit){

        return performanceInitService.updatePerformanceInit(performanceInit);
    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:init')")
    public Results deleteUser(PerformanceInit performanceInit){
        boolean b = performanceInitService.removeById(performanceInit.getId());
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

}


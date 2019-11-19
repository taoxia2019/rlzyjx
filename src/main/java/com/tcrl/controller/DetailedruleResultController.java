package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.tcrl.base.result.Results;
import com.tcrl.entity.Department;
import com.tcrl.entity.DetailedruleResult;
import com.tcrl.entity.Users;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.DetailedruleResultService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.GetSecurityUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-10-23
 */
@Controller
@RequestMapping("/detailedruleResult")
public class DetailedruleResultController {

    @Autowired
    private DetailedruleResultService detailedruleResultService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DepartmentService departmentService;



    @RequestMapping("/add")
    @ResponseBody
    //kpi:performance:query
    @PreAuthorize("hasAuthority('kpi:performance:add')")
    public Results addDetailedruleResult(DetailedruleResult drr){

        //获取目前登录用户

        String username = GetSecurityUsername.getSecurityUsername();
        Users username1 = usersService.getOne(new QueryWrapper<Users>().eq("username", username));
        //设置值

        drr.setKaohedanwei(username1.getDept());

        boolean save = detailedruleResultService.save(drr);
        if(save==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    @RequestMapping("/getResult")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public Results getDetailedruleResult(){
        //获取目前登录用户

        String  username = GetSecurityUsername.getSecurityUsername();
        Users username1 = usersService.getOne(new QueryWrapper<Users>().eq("username", username));

        QueryWrapper<DetailedruleResult> byKaohebumen = new QueryWrapper<DetailedruleResult>();
        byKaohebumen.eq("kaohedanwei", username1.getDept());
        byKaohebumen.eq("kaoheyuefen", DateUtils.getMonth());
        int count = detailedruleResultService.count(byKaohebumen);
        List<DetailedruleResult> results = detailedruleResultService.list(byKaohebumen);
        List<DetailedruleResult> resultsSortById = results.stream()
                .sorted(Comparator.comparing(DetailedruleResult::getId).reversed())
                .collect(Collectors.toList());
        if(results!=null){
            return Results.success(count,resultsSortById);
        }else {
            return Results.failure();
        }
    }

    //跳转编辑页面
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('kpi:performance:edit')")
    public String editUser(Model model,DetailedruleResult detailedruleResult){

        DetailedruleResult byId = detailedruleResultService.getById(detailedruleResult.getId());
        //获取部门start
        List<Department> depts = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept : depts) {
            if(dept.getDeptName().equals(byId.getBeikaohedanwei())){
                dept.setFlag(true);
                break;
            }
        }
        //获取部门end
        model.addAttribute("detailedruleResult",byId);
        model.addAttribute("departments",depts);
        return "/performance/detailedruleResult-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:edit')")
    public Results updateUser(DetailedruleResult detailedruleResult){
        String beikaohedanweiId = detailedruleResult.getBeikaohedanwei();
        String deptName = departmentService.getById(Integer.parseInt(beikaohedanweiId)).getDeptName();
        detailedruleResult.setBeikaohedanwei(deptName);
        boolean b = detailedruleResultService.updateById(detailedruleResult);
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:del')")
    public Results deleteUser(DetailedruleResult detailedruleResult){
        boolean b = detailedruleResultService.removeById(detailedruleResult.getId());
        if(b==true){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    @RequestMapping("/findByDeptResult")
    @ResponseBody
    @PreAuthorize("hasAuthority('kpi:performance:query')")
    public  Results findByDeptResult(String kaoheyuefen,String beikaohedanwei){
        System.out.println("===========");
        System.out.println(kaoheyuefen+"-----"+beikaohedanwei);

        if(null==beikaohedanwei&&null==kaoheyuefen) {
            QueryWrapper<DetailedruleResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", "铸轧分厂");
            List<DetailedruleResult> detailedruleResults = detailedruleResultService.list(performanceResultQueryWrapper);
            int count = detailedruleResultService.count(performanceResultQueryWrapper);
            return Results.success(count,detailedruleResults);
        }else if("".equals(kaoheyuefen)&&null!=beikaohedanwei){
            QueryWrapper<DetailedruleResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", DateUtils.getMonth());
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<DetailedruleResult> detailedruleResults = detailedruleResultService.list(performanceResultQueryWrapper);
            int count = detailedruleResultService.count(performanceResultQueryWrapper);
            return Results.success(count,detailedruleResults);

        } else {
            QueryWrapper<DetailedruleResult> performanceResultQueryWrapper = new QueryWrapper<>();
            performanceResultQueryWrapper.eq("kaoheyuefen", kaoheyuefen);
            performanceResultQueryWrapper.eq("beikaohedanwei", beikaohedanwei);
            List<DetailedruleResult> detailedruleResults = detailedruleResultService.list(performanceResultQueryWrapper);
            int count = detailedruleResultService.count(performanceResultQueryWrapper);
            return Results.success(count,detailedruleResults);
        }



    }



}


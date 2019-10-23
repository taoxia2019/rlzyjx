package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.tcrl.base.result.Results;
import com.tcrl.entity.DetailedruleResult;
import com.tcrl.entity.Users;
import com.tcrl.service.DetailedruleResultService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.GetSecurityUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping("/add")
    @ResponseBody
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
    public Results getDetailedruleResult(){
        //获取目前登录用户

        String  username = GetSecurityUsername.getSecurityUsername();
        Users username1 = usersService.getOne(new QueryWrapper<Users>().eq("username", username));

        QueryWrapper<DetailedruleResult> byKaohebumen = new QueryWrapper<DetailedruleResult>();
        byKaohebumen.eq("kaohedanwei", username1.getDept());
        byKaohebumen.eq("kaoheyuefen", DateUtils.getMonth());
        int count = detailedruleResultService.count(byKaohebumen);
        List<DetailedruleResult> results = detailedruleResultService.list(byKaohebumen);
        results.stream().map(r->r.getKaohejine()).forEach(System.out::println);
        if(results!=null){
            return Results.success(count,results);
        }else {
            return Results.failure();
        }
    }

}


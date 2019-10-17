package com.tcrl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.Results;
import com.tcrl.entity.UserRole;
import com.tcrl.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
@Controller
@RequestMapping("/userrole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /*@RequestMapping("getUserRoleByUserId")
    @ResponseBody
    public Results getUserRoleByUserId(Integer userId){

        UserRole ur = userRoleService.getOne(new QueryWrapper<UserRole>().eq("userid", userId));
        if(ur!=null){
            return Results.success(ur);
        }else {
            return Results.failure();
        }
    }*/

}


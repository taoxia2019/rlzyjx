package com.tcrl.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.entity.Department;
import com.tcrl.entity.Users;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.DateUtils;
import com.tcrl.utils.GetSecurityUsername;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName ApiController
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/9/29 14:33
 * @Version 1.0
 */

//实现路由功能
@RequestMapping("${api-url}")
@Controller
public class ApiController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UsersService usersService;

    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView mv, String pageName, Model model) {
        mv.setViewName(pageName);
        String dept = usersService.getOne(new QueryWrapper<Users>()
                .eq("username", GetSecurityUsername.getSecurityUsername()))
                .getDept();
        List<Department> ids = departmentService.list(new QueryWrapper<Department>().ne("id", 1));
        if("admin".equals(GetSecurityUsername.getSecurityUsername())) {
            for (Department id : ids) {
                if ("铸轧分厂".equals(id.getDeptName())) {
                    id.setFlag(true);
                    break;
                }
            }
        }else {
                for (Department id : ids) {
                    if(dept.equals(id.getDeptName())){
                        id.setFlag(true);
                        break;
                    }
        }

        }
        model.addAttribute("kaoheyuefen", DateUtils.getMonth());
        model.addAttribute("departments",ids);
        return mv;
    }




}

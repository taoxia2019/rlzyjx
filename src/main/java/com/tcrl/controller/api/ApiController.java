package com.tcrl.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/getPage")
    public ModelAndView getPage(ModelAndView mv,String pageName){
        mv.setViewName(pageName);
        return mv;
    }
}

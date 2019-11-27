package com.tcrl.controller;

import com.tcrl.base.result.PageTableRequest;
import com.tcrl.entity.EmpChanliangguagou;
import com.tcrl.utils.StrUtil;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.tcrl.base.result.Results;

import com.tcrl.service.EmpChanliangguagouService;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/empChanliangguagous")
@Slf4j
public class EmpChanliangguagouController {

    @Autowired
    private EmpChanliangguagouService empChanliangguagouService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    @ResponseBody
    public Results save(EmpChanliangguagou empChanliangguagou) {
      return  empChanliangguagouService.save(empChanliangguagou);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Results get(@PathVariable Integer id) {
        return empChanliangguagouService.getById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改")
    public Results  update(EmpChanliangguagou empChanliangguagou) {
       return empChanliangguagouService.update(empChanliangguagou);
    }

    @GetMapping("/listPage")
    @ApiOperation(value = "列表")
    @ResponseBody
    public Results<EmpChanliangguagou> list(PageTableRequest request, EmpChanliangguagou empChanliangguagou) {
          request.countOffset();

        return empChanliangguagouService.getByPage(request.getOffset(), request.getLimit());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除")
    @ResponseBody
    public Results delete(EmpChanliangguagou empChanliangguagou) {
        return empChanliangguagouService.delete( empChanliangguagou);
    }

   @ApiOperation(value = "编辑页面", notes = "跳转到菜单信息编辑页面")//描述
   @RequestMapping(value = "/addOrEdit", method = RequestMethod.GET)
    public ModelAndView roleEdit(Model model, HttpServletRequest request, EmpChanliangguagou empChanliangguagou) {
        EmpChanliangguagou newEmpChanliangguagou = new EmpChanliangguagou();
        if(0 != empChanliangguagou.getId()){
            newEmpChanliangguagou = empChanliangguagouService.getEmpChanliangguagouById(empChanliangguagou.getId());
        }
        model.addAttribute("empChanliangguagou",newEmpChanliangguagou);
        ModelAndView modelAndView =  new ModelAndView("empChanliangguagou/addEmpChanliangguagou");
        return modelAndView;
    }
}

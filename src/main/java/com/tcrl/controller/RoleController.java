package com.tcrl.controller;



import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.Results;
import com.tcrl.dto.RoleDTO;
import com.tcrl.entity.Role;

import com.tcrl.service.RolePermissionService;
import com.tcrl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;

import javax.xml.transform.Result;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ResponseBody
    @RequestMapping("/all")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Results<Role> getRoleAll(){

        return roleService.selectRoleAll();
    }

    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Results<Role> getRole(PageTableRequest page){
        page.countOffset();

        return roleService.getAllRolesByPage(page.getOffset(),page.getLimit());
    }

    @GetMapping("/findByFuzzyRoleName")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Results<Role> findByFuzzyRoleName(PageTableRequest page,String rolename){
        page.countOffset();

        return roleService.findByFuzzyRoleName(rolename,page.getOffset(),page.getLimit());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public String addRole(Model model){
        model.addAttribute("role",new Role());
        return "role/role-add2";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:add')")
    public Results<Role> saveRole(@RequestBody RoleDTO roleDTO){
        roleDTO.setCreatetime(new Date());
        roleDTO.setUpdatetime(new Date());
        return roleService.saveRole(roleDTO);
    }

    //跳转编辑页面
    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public String editUser(Model model,RoleDTO roleDTO){
        Role role1 = roleService.getById(roleDTO.getId());
        model.addAttribute(role1);
        return "role/role-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:edit')")
    public Results<Role> updateRole(@RequestBody RoleDTO roleDTO){
        roleDTO.setUpdatetime(new Date());
        roleService.updateRole(roleDTO);

        return Results.success();
    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:del')")
    public Results deleteUser(RoleDTO roleDTO){
        return roleService.delete(roleDTO.getId());

    }

    String pattern = "yyyy-MM-dd";
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器

    }
}


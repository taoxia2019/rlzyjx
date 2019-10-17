package com.tcrl.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tcrl.base.result.PageTableRequest;
import com.tcrl.base.result.ResponseCode;
import com.tcrl.base.result.Results;
import com.tcrl.dto.RoleDTO;
import com.tcrl.dto.UsersDTO;
import com.tcrl.entity.Department;
import com.tcrl.entity.Role;
import com.tcrl.entity.UserRole;
import com.tcrl.entity.Users;
import com.tcrl.service.DepartmentService;
import com.tcrl.service.RoleService;
import com.tcrl.service.UserRoleService;
import com.tcrl.service.UsersService;
import com.tcrl.utils.MD5;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.time.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/findByFuzzyUsername")
    @ResponseBody
    public Results<Users> findByFuzzyUsername(PageTableRequest page,String username){
        page.countOffset();

        return usersService.getByFuzzyUsername(username,page.getOffset(),page.getLimit());
    }

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "分页获取用户信息",notes = "分页获取用户信息")
    @ApiImplicitParam(name="page",value="分页查询实体类",required = false)
    public Results<Users> getUsers(PageTableRequest page){
        page.countOffset();

        return usersService.getAllUsersByPage(page.getOffset(),page.getLimit());
    }

    @PostMapping("/changePassword")
    @ApiOperation(value="修改密码")
    @ResponseBody
    public Results<Users> changePassword(String username,String oldPassword,String newPassword){
        return usersService.changePassword(username,oldPassword,newPassword);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public String addUser(Model model){
        model.addAttribute(new Users());
        model.addAttribute("roles",roleService.list());

        return "user/user-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Results<Users> saveUser(UsersDTO usersDTO){
        Users user=null;
        user=usersService.getUserByPhone(usersDTO.getPhone());
        if(user!=null &&!(user.getId().equals(usersDTO.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        usersDTO.setStatus(1);
        usersDTO.setPassword(new BCryptPasswordEncoder().encode(usersDTO.getPassword()));
        return usersService.saveUsers(usersDTO);
    }

    //跳转编辑页面
    @GetMapping("/edit")
    public String editUser(Model model,Users users){
        Users user = usersService.getById(users.getId());
        List<UserRole> userRoles = userRoleService.
                list(new QueryWrapper<UserRole>().eq("userid", users.getId()));
        List<Role> roleList = roleService.list();
        List<RoleDTO> roleDTOS=new ArrayList<>();
        //遍历角色表，复制到角色的传输对象，根据userrole表的情况，并对字段flag进行重新赋值
        roleList.stream().forEach(r->{
            //建立roleDTO对象
            RoleDTO roleDTO=new RoleDTO();
            //拷贝
            BeanUtils.copyProperties(r,roleDTO);
            //赋值flag
            userRoles.stream().forEach(rd->{
                //如果不等于空
                if(null!=rd.getRoleid()) {
                    //赋值给DTO
                    if (rd.getRoleid() == roleDTO.getId()) {
                        roleDTO.setFlag(true);
                    }
                }
            });
            //加入list
            roleDTOS.add(roleDTO);
        });
        //获取部门start
        List<Department> deptByUserNames = departmentService.
                list(new QueryWrapper<Department>().
                        eq("deptName", user.getDept()));
        Department deptByUsername = deptByUserNames.get(0);
        List<Department> depts = departmentService.
                list(new QueryWrapper<Department>().ne("id", 1));
        for (Department dept : depts) {
            if(dept.getId().equals(deptByUsername.getId())){
                dept.setFlag(true);
                break;
            }
        }
        //获取部门end
        model.addAttribute(user);
        model.addAttribute("roles",roleDTOS);
        model.addAttribute("departments",depts);
        return "user/user-edit";
    }

    @PostMapping("/edit")
    @ResponseBody
    public Results<Users> updateUser(UsersDTO usersDTO){
        Users user=null;

       /* user=usersService.getByPhone(usersDTO.getPhone());
        if(user!=null ||(user.getId().equals(usersDTO.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }*/
        return usersService.updateUsers(usersDTO);
    }

    //删除
    @GetMapping("/delete")
    @ResponseBody
    public Results deleteUser(UsersDTO usersDTO){
        int count = usersService.deleteUserByid(usersDTO.getId());
        if(count>0){
            return Results.success();
        }else {
            return Results.failure();
        }
    }


    String pattern="yyyy-MM-dd";

    @InitBinder
    public void initBinder(WebDataBinder binder,WebRequest request){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        binder.registerCustomEditor(Date.class,new CustomDateEditor(formatter,true));
    }

}


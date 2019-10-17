package com.tcrl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tcrl.base.result.Myresult;
import com.tcrl.base.result.Results;
import com.tcrl.dao.RoleMapper;
import com.tcrl.dao.UserRoleMapper;
import com.tcrl.dao.UsersMapper;
import com.tcrl.dto.UsersDTO;
import com.tcrl.entity.UserRole;
import com.tcrl.entity.Users;
import com.tcrl.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public Results<Users> getAllUsersByPage(Integer offset, Integer limit) {

        return Results.success(usersMapper.countAllUsers().intValue(),usersMapper.getallUsersByPage(offset,limit));
    }

    @Override
    public Results<Users> saveUsers(UsersDTO usersDTO) {
        List<Integer> roleIds = usersDTO.getRoleIds();
        if (roleIds != null) {
            usersDTO.setCreatetime(new Date());
            usersDTO.setUpdatetime(new Date());
            usersMapper.insert(usersDTO);
            roleIds.stream().forEach(i->{
                UserRole userRole = new UserRole();
                userRole.setRoleid(i);
                userRole.setUserid(usersDTO.getId());
                userRoleMapper.insert(userRole);
            });
            return Results.success();
        } else {
            return Results.failure();
        }
    }



    @Override
    public Results<Users> updateUsers(UsersDTO usersDTO) {
        List<Integer> roleIds = usersDTO.getRoleIds();
        if (roleIds != null) {
            //更新用户表
            usersMapper.updateById(usersDTO);
            //删除该用户原有关联角色
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("userid",usersDTO.getId()));
            //更新用户角色表
            roleIds.stream().forEach(i->{
                UserRole userRole = new UserRole();
                userRole.setRoleid(i);
                userRole.setUserid(usersDTO.getId());
                userRoleMapper.insert(userRole);
            });
            return Results.success();
        } else {
            return Results.failure();
        }
    }

    @Override
    public Users getUserByPhone(String phone) {
        return usersMapper.getUserByPhone(phone);
    }


    public int deleteUserByid(Integer id){

        int id1 = userRoleMapper.delete(new QueryWrapper<UserRole>().eq("userid", id));
        return usersMapper.deleteById(id);
    }


    @Override
    public Results<Users> getByFuzzyUsername(String username, Integer offset, Integer limit) {
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.like("username",username);
        usersMapper.selectCount(usersQueryWrapper);

        Page<Users> page=new Page<>(offset,limit);

        IPage<Users> usersIPage = usersMapper.selectPage(page, usersQueryWrapper);
        Long total = usersIPage.getTotal();
        List<Users> records = usersIPage.getRecords();

        return Results.success(total.intValue(),records);
    }

    @Override
    public Results<Users> changePassword(String username, String oldPassword, String newPassword) {
        Users u=usersMapper.getUser("admin");
        if(u==null){
            return Results.failure(1,"用户不存在");
        }
        if(!new BCryptPasswordEncoder().encode(oldPassword).equals(u.getPassword())){
            return Results.failure(1,"旧密码错误");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(newPassword);
        u.setPassword(encodedPassword);
        usersMapper.updateById(u);
        //usersMapper.changePassword(u.getId(),new BCryptPasswordEncoder().encode(newPassword));

        return Results.success();
    }
}

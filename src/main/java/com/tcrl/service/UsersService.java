package com.tcrl.service;

import com.tcrl.base.result.Myresult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tcrl.base.result.Results;
import com.tcrl.dto.UsersDTO;
import com.tcrl.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
public interface UsersService extends IService<Users> {


    Results<Users> getAllUsersByPage(Integer offset, Integer limit);

    Results<Users> saveUsers(UsersDTO usersDTO);



    Results<Users> updateUsers(UsersDTO usersDTO);

    Users getUserByPhone(String phone);

    int deleteUserByid(Integer id);

    Results<Users> getByFuzzyUsername(String username, Integer offset, Integer limit);

    Results<Users> changePassword(String username, String oldPassword, String newPassword);
}

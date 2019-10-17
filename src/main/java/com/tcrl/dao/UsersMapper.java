package com.tcrl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcrl.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {
    @Select("select * from sys_users t where t.username=#{username}")
    Users getUser(String username);
    @Select("select * from sys_users t order by t.id limit #{startPosition},#{limit}")
    List<Users> getallUsersByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Select("select * from sys_users t where t.phone=#{phone}")
    Users getUserByPhone(String phone);
    @Select("select count(*) from sys_users t")
    Long countAllUsers();

    @Select("select count(*) from sys_users t where t.username like '%${username}%'")
    Long getCountByFuzzyUsername(@Param("username") String username);

    @Select("select * from sys_users t where t.username like '%${username}%' limit #{offset},#{limit}")
    List<Users> getByFuzzyUsernameByPage(@Param("username") String username, @Param("offset") Integer offset, @Param("limit") Integer limit);
}

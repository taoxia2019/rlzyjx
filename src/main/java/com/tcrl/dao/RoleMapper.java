package com.tcrl.dao;

import com.tcrl.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select count(*) from sys_role t")
    Long countAllRoles();

    @Select("select * from sys_role t order by t.id limit #{startPosition},#{limit}")
    List<Role> getallRolesByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);
}

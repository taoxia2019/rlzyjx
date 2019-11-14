package com.tcrl.dao;

import com.tcrl.entity.Deptemployee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author taoxia
 * @since 2019-11-12
 */
public interface DeptemployeeMapper extends BaseMapper<Deptemployee> {

    @Select("select count(*) from emp_deptemployee")
    Long countAllDeptemployee();

    @Select("select count(*) from emp_deptemployee t where t.dept=#{dept}")
    Long countAllDeptemployeeByDept(String dept);

    @Select("select * from emp_deptemployee t order by t.id limit #{startPosition},#{limit}")
    List<Deptemployee> getallDeptemployeeByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Select("select * from emp_deptemployee t where t.dept=#{dept} order by t.id limit #{startPosition},#{limit}")
    List<Deptemployee> getallDeptemployeeByDeptByPage(@Param("dept") String dept,@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);
}

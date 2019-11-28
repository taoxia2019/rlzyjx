package com.tcrl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tcrl.entity.Chanliangguagou;
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
 * @since 2019-11-14
 */
@Repository
public interface ChanliangguagouMapper extends BaseMapper<Chanliangguagou> {

    @Select("select sum(t.guagoujine) from emp_chanliangguagou t where t.dept=#{dept} and t.kaoheyuefen=#{kaoheyuefen}")
    Double sumGuagoujineByDept(@Param("dept") String dept,@Param("kaoheyuefen") String kaoheyuefen);

    @Select("select sum(t.guagoujine) from emp_chanliangguagou t where t.kaoheyuefen=#{kaoheyuefen}")
    Double sumGuagoujineByKaoheyuefen(@Param("kaoheyuefen") String kaoheyuefen);

    @Select("select count(*) from emp_chanliangguagou")
    Long countAllDeptGuagoujineByDept();

    @Select("select count(*) from emp_chanliangguagou t where t.dept=#{dept}")
    Long countAllDeptGuagoujineByDept(String dept);

    @Select("select * from emp_chanliangguagou t order by t.id limit #{startPosition},#{limit}")
    List<Chanliangguagou> getallDeptGuagoujineByDept(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Select("select * from emp_chanliangguagou t where t.dept=#{dept} order by t.id limit #{startPosition},#{limit}")
    List<Chanliangguagou> getallDeptGuagoujineByDept(@Param("dept") String dept,@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);
}

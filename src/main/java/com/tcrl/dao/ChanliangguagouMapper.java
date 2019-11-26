package com.tcrl.dao;

import com.tcrl.entity.Chanliangguagou;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}

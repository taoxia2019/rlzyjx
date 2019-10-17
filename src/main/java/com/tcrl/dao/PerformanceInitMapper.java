package com.tcrl.dao;

import com.tcrl.entity.PerformanceInit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2019-10-17
 */
@Repository
public interface PerformanceInitMapper extends BaseMapper<PerformanceInit> {
    @Select("select count(*) from kpi_performance_init")
    Long countAllPerformances();

    @Select("select * from kpi_performance_init t order by t.id limit #{startPosition},#{limit}")
    List<PerformanceInit> getallPerformancesByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

}

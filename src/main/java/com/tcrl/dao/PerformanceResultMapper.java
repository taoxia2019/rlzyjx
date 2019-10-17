package com.tcrl.dao;

import com.tcrl.entity.PerformanceResult;
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
 * @since 2019-10-17
 */
public interface PerformanceResultMapper extends BaseMapper<PerformanceResult> {
    @Select("select count(*) from kpi_performance_result where kaoheyuefen=#{kaoheyuefen} and kaohedanwei=#{kaohedanwei} ")
    Long countAllPerformances(@Param("kaoheyuefen") String kaoheyuefen,@Param("kaohedanwei") String kaohedanwei);

    @Select("select * from kpi_performance_init t order by t.id limit #{startPosition},#{limit} where kaoheyuefen=#{kaoheyuefen} and kaohedanwei=#{kaohedanwei} ")
    List<PerformanceResult> getallPerformancesByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit,@Param("kaoheyuefen") String kaoheyuefen,@Param("kaohedanwei") String kaohedanwei);

    @Select("select count(*) from kpi_performance_result where kaoheyuefen=#{kaoheyuefen}")
    Long countAllPerformances(@Param("kaoheyuefen") String kaoheyuefen);

    @Select("select * from kpi_performance_init t order by t.id limit #{startPosition},#{limit} where kaoheyuefen=#{kaoheyuefen}")
    List<PerformanceResult> getallPerformancesByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit,@Param("kaoheyuefen") String kaoheyuefen);
}

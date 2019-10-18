package com.tcrl.dao;

import com.tcrl.entity.PerformanceResult;
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
public interface PerformanceResultMapper extends BaseMapper<PerformanceResult> {
    @Select("select count(*) from kpi_performance_result t where t.kaoheyuefen=#{kaoheyuefen} and t.kaohedanwei=#{kaohedanwei} ")
    Long countAllPerformances(@Param("kaoheyuefen") String kaoheyuefen,@Param("kaohedanwei") String kaohedanwei);

    @Select("select * from kpi_performance_result t where t.kaoheyuefen=#{kaoheyuefen} and t.kaohedanwei=#{kaohedanwei} order by t.id limit #{startPosition},#{limit} ")
    List<PerformanceResult> getallPerformancesByPage(@Param("kaoheyuefen") String kaoheyuefen,@Param("kaohedanwei") String kaohedanwei,@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Select("select count(*) from kpi_performance_result t where t.kaoheyuefen=#{kaoheyuefen}")
    Long countAllPerformances1(@Param("kaoheyuefen") String kaoheyuefen);

    @Select("select * from kpi_performance_result t where t.kaoheyuefen=#{kaoheyuefen} order by t.id limit #{startPosition},#{limit}")
    List<PerformanceResult> getallPerformancesByPage1(@Param("kaoheyuefen") String kaoheyuefen,@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);
}

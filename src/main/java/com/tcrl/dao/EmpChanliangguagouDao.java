package com.tcrl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import com.tcrl.entity.EmpChanliangguagou;

@Repository
public interface EmpChanliangguagouDao extends BaseMapper<EmpChanliangguagou> {

    @Select("select * from emp_chanliangguagou t where t.id = #{id}")
    EmpChanliangguagou getById(Integer id);

    @Delete("delete from emp_chanliangguagou where id = #{id}")
    int delete(EmpChanliangguagou empChanliangguagou);

    int update(EmpChanliangguagou empChanliangguagou);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp_chanliangguagou(xingming, dept, gangwei, guagoudanwei, gangxu, dangci, gangweigongzi, jixiaogongzi, guagoujine, kaoheyuefen, beiyongziduan2, beiyongziduan3, beiyongziduan4) values(#{xingming}, #{dept}, #{gangwei}, #{guagoudanwei}, #{gangxu}, #{dangci}, #{gangweigongzi}, #{jixiaogongzi}, #{guagoujine}, #{kaoheyuefen}, #{beiyongziduan2}, #{beiyongziduan3}, #{beiyongziduan4})")
    int save(EmpChanliangguagou empChanliangguagou);
    
    int count();

    List<EmpChanliangguagou> list(@Param("offset") Integer offset, @Param("limit") Integer limit);

}

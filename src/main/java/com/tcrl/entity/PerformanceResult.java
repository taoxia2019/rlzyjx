package com.tcrl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author taoxia
 * @since 2019-10-17
 */
@TableName("kpi_performance_result")
@Data
public class PerformanceResult implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String beikaohedanwei;

    private String beizhu;

    private String biaozhun;

    private String caozuofu;

    private String danwei;

    private String kaohedanwei;

    private Double kaohejieguo;

    private String kaohexiangmu;

    private String kaoheyuefen;

    private Double mubiaozhi;

    private Double shijishi;

    private String zhouqi;

    private Integer status;
}

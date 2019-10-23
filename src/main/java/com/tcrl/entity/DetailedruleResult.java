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
 * @since 2019-10-23
 */
@TableName("kpi_detailedrule_result")
@Data
public class DetailedruleResult implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String kaoheyuefen;
    private String beikaohedanwei;
    private String kaoheshiyou;
    private Double kaohejine;
    private String kaohedanwei;
    private Integer status;
    private String xiangmumingcheng;

}

package com.tcrl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author taoxia
 * @since 2019-09-30
 */
@TableName("sys_role")
@Data
public class Role implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String descirption;

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updatetime;


}

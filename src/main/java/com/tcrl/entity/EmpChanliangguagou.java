package com.tcrl.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("emp_chanliangguagou")
public class EmpChanliangguagou implements Serializable {

	private static final long serialVersionUID=1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private String xingming;
	private String dept;
	private String gangwei;
	private String guagoudanwei;
	private String gangxu;
	private Integer dangci;
	private Double gangweigongzi;
	private Double jixiaogongzi;
	private Double guagoujine;
	private String kaoheyuefen;
	private String beiyongziduan2;
	private String beiyongziduan3;
	private String beiyongziduan4;


}

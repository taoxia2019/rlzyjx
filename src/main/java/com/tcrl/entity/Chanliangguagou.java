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
 * @since 2019-11-28
 */
@TableName("emp_chanliangguagou")
@Data
public class Chanliangguagou implements Serializable {

	private static final long serialVersionUID=1L;

	/**
	 * ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 姓名
	 */
	private String xingming;

	/**
	 * 部门
	 */
	private String dept;

	/**
	 * 岗位
	 */
	private String gangwei;

	/**
	 * 挂钩单位
	 */
	private String guagoudanwei;

	/**
	 * 岗序
	 */
	private String gangxu;

	/**
	 * 档次
	 */
	private Integer dangci;

	/**
	 * 岗位工资
	 */
	private Double gangweigongzi;

	/**
	 * 绩效工资
	 */
	private Double jixiaogongzi;

	/**
	 * 挂钩金额
	 */
	private Double guagoujine;

	/**
	 * 考核月份
	 */
	private String kaoheyuefen;

	/**
	 * 备用字段2
	 */
	private String beiyongziduan2;

	/**
	 * 备用字段3
	 */
	private String beiyongziduan3;

	/**
	 * 备用字段4
	 */
	private String beiyongziduan4;


}

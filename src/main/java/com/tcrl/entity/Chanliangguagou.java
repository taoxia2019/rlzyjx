package com.tcrl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author taoxia
 * @since 2019-11-14
 */
//产量挂钩
@TableName("emp_chanliangguagou")
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGangwei() {
        return gangwei;
    }

    public void setGangwei(String gangwei) {
        this.gangwei = gangwei;
    }

    public String getGuagoudanwei() {
        return guagoudanwei;
    }

    public void setGuagoudanwei(String guagoudanwei) {
        this.guagoudanwei = guagoudanwei;
    }

    public String getGangxu() {
        return gangxu;
    }

    public void setGangxu(String gangxu) {
        this.gangxu = gangxu;
    }

    public Integer getDangci() {
        return dangci;
    }

    public void setDangci(Integer dangci) {
        this.dangci = dangci;
    }

    public Double getGangweigongzi() {
        return gangweigongzi;
    }

    public void setGangweigongzi(Double gangweigongzi) {
        this.gangweigongzi = gangweigongzi;
    }

    public Double getJixiaogongzi() {
        return jixiaogongzi;
    }

    public void setJixiaogongzi(Double jixiaogongzi) {
        this.jixiaogongzi = jixiaogongzi;
    }

    public Double getGuagoujine() {
        return guagoujine;
    }

    public void setGuagoujine(Double guagoujine) {
        this.guagoujine = guagoujine;
    }

    public String getKaoheyuefen() {
        return kaoheyuefen;
    }

    public void setKaoheyuefen(String kaoheyuefen) {
        this.kaoheyuefen = kaoheyuefen;
    }

    public String getBeiyongziduan2() {
        return beiyongziduan2;
    }

    public void setBeiyongziduan2(String beiyongziduan2) {
        this.beiyongziduan2 = beiyongziduan2;
    }

    public String getBeiyongziduan3() {
        return beiyongziduan3;
    }

    public void setBeiyongziduan3(String beiyongziduan3) {
        this.beiyongziduan3 = beiyongziduan3;
    }

    public String getBeiyongziduan4() {
        return beiyongziduan4;
    }

    public void setBeiyongziduan4(String beiyongziduan4) {
        this.beiyongziduan4 = beiyongziduan4;
    }

    @Override
    public String toString() {
        return "Chanliangguagou{" +
        "id=" + id +
        ", xingming=" + xingming +
        ", dept=" + dept +
        ", gangwei=" + gangwei +
        ", guagoudanwei=" + guagoudanwei +
        ", gangxu=" + gangxu +
        ", dangci=" + dangci +
        ", gangweigongzi=" + gangweigongzi +
        ", jixiaogongzi=" + jixiaogongzi +
        ", guagoujine=" + guagoujine +
        ", kaoheyuefen=" + kaoheyuefen +
        ", beiyongziduan2=" + beiyongziduan2 +
        ", beiyongziduan3=" + beiyongziduan3 +
        ", beiyongziduan4=" + beiyongziduan4 +
        "}";
    }
}

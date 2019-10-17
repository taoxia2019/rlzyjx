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
 * @since 2019-10-17
 */
@TableName("kpi_performance_init")
public class PerformanceInit implements Serializable {

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeikaohedanwei() {
        return beikaohedanwei;
    }

    public void setBeikaohedanwei(String beikaohedanwei) {
        this.beikaohedanwei = beikaohedanwei;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getBiaozhun() {
        return biaozhun;
    }

    public void setBiaozhun(String biaozhun) {
        this.biaozhun = biaozhun;
    }

    public String getCaozuofu() {
        return caozuofu;
    }

    public void setCaozuofu(String caozuofu) {
        this.caozuofu = caozuofu;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getKaohedanwei() {
        return kaohedanwei;
    }

    public void setKaohedanwei(String kaohedanwei) {
        this.kaohedanwei = kaohedanwei;
    }

    public Double getKaohejieguo() {
        return kaohejieguo;
    }

    public void setKaohejieguo(Double kaohejieguo) {
        this.kaohejieguo = kaohejieguo;
    }

    public String getKaohexiangmu() {
        return kaohexiangmu;
    }

    public void setKaohexiangmu(String kaohexiangmu) {
        this.kaohexiangmu = kaohexiangmu;
    }

    public String getKaoheyuefen() {
        return kaoheyuefen;
    }

    public void setKaoheyuefen(String kaoheyuefen) {
        this.kaoheyuefen = kaoheyuefen;
    }

    public Double getMubiaozhi() {
        return mubiaozhi;
    }

    public void setMubiaozhi(Double mubiaozhi) {
        this.mubiaozhi = mubiaozhi;
    }

    public Double getShijishi() {
        return shijishi;
    }

    public void setShijishi(Double shijishi) {
        this.shijishi = shijishi;
    }

    public String getZhouqi() {
        return zhouqi;
    }

    public void setZhouqi(String zhouqi) {
        this.zhouqi = zhouqi;
    }

    @Override
    public String toString() {
        return "PerformanceInit{" +
        "id=" + id +
        ", beikaohedanwei=" + beikaohedanwei +
        ", beizhu=" + beizhu +
        ", biaozhun=" + biaozhun +
        ", caozuofu=" + caozuofu +
        ", danwei=" + danwei +
        ", kaohedanwei=" + kaohedanwei +
        ", kaohejieguo=" + kaohejieguo +
        ", kaohexiangmu=" + kaohexiangmu +
        ", kaoheyuefen=" + kaoheyuefen +
        ", mubiaozhi=" + mubiaozhi +
        ", shijishi=" + shijishi +
        ", zhouqi=" + zhouqi +
        "}";
    }
}

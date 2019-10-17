package com.tcrl.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PageTableRequest
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/9/30 9:59
 * @Version 1.0
 */
@Data
public class PageTableRequest implements Serializable {
    //当前页
    private Integer page;
    //每页条数
    private Integer limit;
    //起始条目
    private Integer offset;
    //获取起始条目方法
    public void countOffset(){
        if(null==this.page||null==this.limit){
            this.offset=0;
            return;
        }
        this.offset=(this.page-1)*this.limit;
    }

}

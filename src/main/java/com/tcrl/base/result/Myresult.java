package com.tcrl.base.result;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Myresult
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/9/29 17:06
 * @Version 1.0
 */
@Data
public class Myresult<T> {
    int count;//数据数量
    Integer code=0;//代码
    String msg="";//信息
    List<T> data;//返回数据类型
}

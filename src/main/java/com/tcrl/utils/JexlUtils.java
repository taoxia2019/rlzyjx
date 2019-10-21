package com.tcrl.utils;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.text.DecimalFormat;

/**
 * @ClassName JexlUtils
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/9/25 11:06
 * @Version 1.0
 */
//动态解析数据库公式，计算结果
public class JexlUtils {
    public static  Double getValue(Double y, Double x, String exper) throws Exception{
        DecimalFormat    df   = new DecimalFormat("######0.00");
        JexlContext jc =new MapContext();

        jc.set("y",y);
        jc.set("x",x);
        Expression expression = new JexlEngine().createExpression(exper);
        Double value=Double.parseDouble(expression.evaluate(jc).toString());
        String format = df.format(value);
        return Double.parseDouble(format);
    }
}

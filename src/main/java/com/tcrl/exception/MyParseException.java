package com.tcrl.exception;

/**
 * @ClassName MyParseException
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/9/29 9:45
 * @Version 1.0
 */
public class MyParseException extends RuntimeException {
    public MyParseException() {
        super("解析错误，请检查你输入的运算符");
    }
}

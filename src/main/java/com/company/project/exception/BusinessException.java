package com.company.project.exception;


/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常消息
     */
    private String msg;




    public BusinessException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}

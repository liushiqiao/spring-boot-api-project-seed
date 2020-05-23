package com.company.project.entry.vo;

import com.company.project.entry.enums.ResultCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 统一API响应结果封装
 */
@Getter
@ToString
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}

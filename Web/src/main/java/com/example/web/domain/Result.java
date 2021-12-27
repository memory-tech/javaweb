package com.example.web.domain;

import java.io.Serializable;

/**
 * @Author Memory
 * @Date 2021/11/8 21:22
 * @Version 1.0
 */
public class Result<T> implements Serializable {
    private Integer code;   //编号
    private String message; //信息
    private T data;         //参数

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

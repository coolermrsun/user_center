package com.szk.user_center.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回通用类
 * author szk
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {
    /**
     * 错误码
     */
    private int code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回信息
     */
    private String message;
    private String description;

    public BaseResponse(int code, T data) {
        this(code,data,"","");
    }
    public BaseResponse(int code, T data, String message) {
        this(code,data,message,"");
    }

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }

}

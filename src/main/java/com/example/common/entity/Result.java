package com.example.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success = false;//是否成功

    //返回码
    private String code;

    //提示信息
    private String message;
    
    //返回装载数据
    private T data;

    public Result() {
    }

    /**
     *
     * @param success 是否成功
     */
    public Result(boolean success) {
        this.success = success;
    }

    /**
     *
     * @param success 是否成功
     * @param message 错误信息
     */
    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     *
     * @param success 是否成功
     * @param code 错误码
     * @param message 错误信息
     */
    public Result(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param code 错误码
     * @param message 错误信息
     */
    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

}

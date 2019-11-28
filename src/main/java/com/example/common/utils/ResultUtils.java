package com.example.common.utils;

import com.example.common.entity.Result;
import com.example.common.enums.StatusCode;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/27 15:47
 */
public class ResultUtils {

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> success(T data){
        Result<T> result= new Result<T>();
        result.setCode("0");
        result.setData(data);
        result.setSuccess(true);
        result.setMessage("操作成功");
        return result;
    }

    /**
     *
     * @return
     */
    public static Result success(){
        return success(null);
    }


    /**
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(String code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result error(StatusCode statusCode){
        Result result = new Result();
        result.setCode(statusCode.getCode());
        result.setMessage(statusCode.getMsg());
        return result;
    }



}

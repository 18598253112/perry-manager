package com.example.common.exception;

import com.example.common.enums.StatusCode;
import lombok.Data;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/27 15:57
 */
@Data
public class CommonException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public CommonException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonException(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

}

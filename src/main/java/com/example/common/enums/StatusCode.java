package com.example.common.enums;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/27 11:35
 */
public enum StatusCode {
    
    ACCOUNT_OR_PASSWORD_ERROR("40002","账号或密码错误!"),
    USER_NOT_EXIST("40003", "用户不存在！");
    private String msg;
    private String code;

    StatusCode(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

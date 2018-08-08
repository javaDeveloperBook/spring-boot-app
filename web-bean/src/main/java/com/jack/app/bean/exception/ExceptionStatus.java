package com.jack.app.bean.exception;

/**
 * @description:定义全局异常状态
 * @author: JackWu
 * @create: 2018-07-31 09:26
 **/
public enum ExceptionStatus {

    //通用公共1-1000
    SUCCESS(0, "succ"),

    AUTH_FAILED(1, "auth failed"),

    BAD_REQUEST(2, "invalid request paramers"),

    PARAM_REQUIRED(3, "parameter required"),

    NOT_FOUND(4, "object not found"),

    ALREADY_EXISTS(5, "object already exists"),

    FORMAT_ERROR(6, "format error"),

    INTERNAL_ERROR(7, "service internal error"),

    //后续业务异常统一添加

    //用户登录异常
    EMAIL_USED(1001,"Email has been used"),

    USERNAME_USED(1002,"UserName has been used");

    private String error;
    private int errno;

    ExceptionStatus(int errno, String error) {
        this.error = error;
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public int getErrno() {
        return errno;
    }
}

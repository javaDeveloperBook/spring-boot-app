package com.jack.app.bean.response;

import com.jack.app.bean.exception.ExceptionStatus;

/**
 * @description: 基本JSON响应格式
 * @author: JackWu
 * @create: 2018-07-31 09:22
 **/
public class BasicResponse {

    protected int errno;
    protected String error;
    private final static BasicResponse instance = new BasicResponse();

    public BasicResponse() {
        this.errno = 0;
        this.error = "succ";
    }

    public BasicResponse(int errno, String error) {
        this.errno = errno;
        this.error = error;
    }

    public BasicResponse(ExceptionStatus status) {
        this.errno = status.getErrno();
        this.error = status.getError();
    }

    public BasicResponse(ExceptionStatus status, String message) {
        this.errno = status.getErrno();
        this.error = status.getError() + " : " + message;
    }

    public int getErrno() {
        return errno;
    }

    public String getError() {
        return error;
    }

    /**
     * 返回默认成功响应信息
     */
    public static BasicResponse success() {
        return instance;
    }

    @Override
    public String toString() {
        return "{\"errno:\"" + errno + ",\"error\":\"" + error + "\"}";
    }

}

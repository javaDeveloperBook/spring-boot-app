package com.jack.app.bean.exception;

import java.io.Serializable;

/**
 * @description: 自定义业务异常类
 * @author: JackWu
 * @create: 2018-07-31 09:52
 **/
public class BusinessException extends RuntimeException implements Serializable {

    private int errno = 500;
    private String error;

    public BusinessException(String error){
        super(error);
        this.error = error;
    }

    public BusinessException(String error,Throwable e){
        super(error,e);
        this.error = error;
    }

    public BusinessException(int errno,String error){
        super(error);
        this.errno = errno;
        this.error = error;
    }

    public BusinessException(String error,int errno,Throwable e){
        super(error,e);
        this.errno = errno;
        this.error = error;
    }

    public BusinessException(ExceptionStatus exceptionStatus,String error){
        super(exceptionStatus.getError() + " : " + error);
        this.errno = exceptionStatus.getErrno();
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}

package com.jack.app.bean.response;

import com.jack.app.bean.exception.ExceptionStatus;

/**
 * @description: 数据响应格式
 * @author: JackWu
 * @create: 2018-07-31 09:32
 **/
public class DataResponse<T> extends BasicResponse{

    protected T data;

    protected <T> DataResponse(){

    }

    public DataResponse(T data){
        this.errno = ExceptionStatus.SUCCESS.getErrno();
        this.error = ExceptionStatus.SUCCESS.getError();
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

}

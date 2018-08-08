package com.jack.app.bean.request;

import com.jack.app.bean.exception.BusinessException;
import com.jack.app.bean.exception.ExceptionStatus;
import org.omg.CORBA.UserException;

/**
 * @description: 分页参数类
 * @author: JackWu
 * @create: 2018-07-31 14:19
 **/
public class PageParam {

    private int page;
    private int size;

    private PageParam(int page, int size) {

        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public static PageParam of(int page, int size) throws UserException {
        if (page < 0) {
            throw new BusinessException(ExceptionStatus.BAD_REQUEST, "the value of page cannot less than 0");
        }
        if (size < 1 || size > 100) {
            throw new BusinessException(ExceptionStatus.BAD_REQUEST, "the value of per_page can not greater than 100 or less than 1");
        }
        return new PageParam(page, size);
    }

}

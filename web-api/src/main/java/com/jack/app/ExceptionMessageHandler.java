package com.jack.app;

import com.jack.app.bean.exception.BusinessException;
import com.jack.app.bean.response.BasicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 全局异常处理类
 * @author: JackWu
 * @create: 2018-08-06 10:10
 **/
@ControllerAdvice
public class ExceptionMessageHandler {

    private Logger logger = LoggerFactory.getLogger(ExceptionMessageHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleBusinessException(BusinessException e, HttpServletRequest request){
        logger.error("Handle BusinessException : " ,e);
        String contentType = request.getHeader("Content-Type");
        String accept = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        //JSON请求返回JSON格式错误信息
        if ((contentType != null && contentType.contains("application/json"))
                || (accept != null && accept.contains("application/json"))
                || ("XMLHttpRequest".equalsIgnoreCase(xRequestedWith))) {
            return new BasicResponse(e.getErrno(), e.getError());
        }else {
            //页面请求返回错误页面
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errno",e.getErrno());
            modelAndView.addObject("error",e.getError());
            modelAndView.addObject("url",request.getRequestURL());
            modelAndView.addObject("stackTrace",e.getStackTrace());
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

}

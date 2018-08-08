package com.jack.app.api.annotation;

import com.jack.app.bean.exception.BusinessException;
import com.jack.app.bean.exception.ExceptionStatus;
import com.jack.app.bean.request.PageParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @description:分页数据处理类
 * @author: JackWu
 * @create: 2018-07-31 14:29
 **/
@Component
public class PageResolver implements HandlerMethodArgumentResolver {

    @Value("${web.pager.param.page}")
    String pageParam;

    @Value("${web..pager.param.size}")
    String sizeParam;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == PageParam.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        int page,size;
        //没有注解
        if (methodParameter.getParameterAnnotation(PageDefault.class) == null){
            try {
                page = Integer.parseInt(nativeWebRequest.getParameter(this.pageParam));
            }catch (NumberFormatException e){
                throw new BusinessException(ExceptionStatus.BAD_REQUEST,"invalid param " + this.pageParam);
            }
            try {
                size = Integer.parseInt(nativeWebRequest.getParameter(this.sizeParam));
            }catch (NumberFormatException e){
                throw new BusinessException(ExceptionStatus.BAD_REQUEST,"invalid param " + this.sizeParam);
            }
        }else {
            //有注解
            try {
                page = Integer.parseInt(nativeWebRequest.getParameter(this.pageParam));
            }catch (NumberFormatException e){
                page = methodParameter.getParameterAnnotation(PageDefault.class).page();
            }
            try {
                size = Integer.parseInt(nativeWebRequest.getParameter(this.sizeParam));
            }catch (NumberFormatException e){
                size = methodParameter.getParameterAnnotation(PageDefault.class).size();
            }
        }

        if (page < 0){
            throw new BusinessException(ExceptionStatus.BAD_REQUEST,"invalid " + this.pageParam + " params");
        }
        if (size < 1){
            throw new BusinessException(ExceptionStatus.BAD_REQUEST,"invalid " + this.sizeParam + " params");
        }

        return PageParam.of(page,size);
    }
}

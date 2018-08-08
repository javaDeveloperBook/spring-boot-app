package com.jack.app.api.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageDefault {
    /**页码,JPA第一页为0*/
    int page() default 0;

    /**每页条目数*/
    int size() default 20;
}

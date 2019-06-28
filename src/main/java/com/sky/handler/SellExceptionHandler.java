package com.sky.handler;

import com.sky.constant.ProjectConstant;
import com.sky.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    //拦截、跳转
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect"
        .concat(ProjectConstant.projectUrl)
        .concat("/seller/login"));
        //TODO
    }
}

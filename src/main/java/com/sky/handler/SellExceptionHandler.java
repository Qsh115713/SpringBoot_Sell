package com.sky.handler;

import com.sky.constant.ProjectConstant;
import com.sky.exception.SellException;
import com.sky.exception.SellerAuthorizeException;
import com.sky.utils.ResultVOUtil;
import com.sky.viewobject.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /*
    当需要把HTTP的状态码也改变时，
     */
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}

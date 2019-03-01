package com.xmcc.House.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class CustomExceptionResolver {

@ExceptionHandler({Exception.class})
    public  String  errorHandler(HttpServletRequest request,Exception e){
    StringBuffer requestURL = request.getRequestURL();
    log.error("异常绝对路径:{}",requestURL);
    log.error("异常信息:{}",e.getMessage());
    log.error("异常原因:{}",e.getCause());
    e.printStackTrace();
    return  "error/500";

}
}

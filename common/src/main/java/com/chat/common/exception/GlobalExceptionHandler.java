package com.chat.common.exception;


import com.chat.common.dto.ResultUtls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mengw
 * Description 统一异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Object serviceExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        log.error("[GlobalExceptionHandler][ServiceException] exception",e);
        return  ResultUtls.fail(e.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public Object runtimeExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        log.error("[GlobalExceptionHandler][RuntimeException] exception",e);
        return  ResultUtls.fail("出现小问题");
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        log.error("[GlobalExceptionHandler][Exception] exception",e);
        return  ResultUtls.fail("系统发生错误");
    }



}

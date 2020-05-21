package com.example.keyanzuul.exception;

import com.example.keyanzuul.entty.Result;
import com.example.keyanzuul.entty.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: cj
 * @DateTime: 2020/3/19 22:28
 * @Description: TODO
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return new Result(ResultCode.SERVER_ERROR.code(),"服务器故障",false);
    }

}

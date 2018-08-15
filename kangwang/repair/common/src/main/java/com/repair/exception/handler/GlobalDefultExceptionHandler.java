package com.repair.exception.handler;

import com.repair.exception.BaseException;
import com.repair.model.response.ReturnValue;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * auther   kangwang
 */

@ControllerAdvice
public class GlobalDefultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> ReturnValue defultExcepitonHandler(HttpServletRequest request, Exception e) {
        ReturnValue returnValue = new ReturnValue();
        if(e instanceof BaseException) {
            BaseException businessException = (BaseException)e;
            returnValue.setMsg(e.getMessage());
            return returnValue;
        }
        //未知错误
        returnValue.setMsg("系统异常！");
        return returnValue;
    }
}

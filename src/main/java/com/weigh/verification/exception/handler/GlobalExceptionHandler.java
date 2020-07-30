package com.weigh.verification.exception.handler;

import com.weigh.verification.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyang
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public CustomException tempErrHandler(CustomException e) {
        return new CustomException(e.getCode(), e.getMsg());
    }
}

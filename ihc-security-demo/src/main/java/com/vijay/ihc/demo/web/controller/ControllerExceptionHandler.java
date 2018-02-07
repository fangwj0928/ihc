package com.vijay.ihc.demo.web.controller;

import com.vijay.ihc.demo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handlerUserNotExistException(UserNotExistException ex) {

        Map<String, Object> result = new HashMap<>();

        result.put("id", ex.getId());

        result.put("message", ex.getMessage());

        return result;
    }
}

package com.example.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseBody
    public String handleException(DateTimeParseException e){
        return "Enter the following date format: YYYY-MM-DD";
    }

}

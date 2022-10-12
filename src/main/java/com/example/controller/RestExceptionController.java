package com.example.controller;

import com.example.model.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class RestExceptionController {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo parseException() {
        ErrorInfo errorInfo = new ErrorInfo(
                UUID.randomUUID(),
                "Can't process this request, please enter the following date format: YYYY-MM-DD",
                LocalDateTime.now()
        );
        log.info("Incorrectly parse format{}", errorInfo.getId());
        return errorInfo;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInfo validationParameters(IllegalArgumentException ex) {
        ErrorInfo errorInfo = new ErrorInfo(
                UUID.randomUUID(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        log.info("Incorrectly entered parameters{}", errorInfo.getId());
        return errorInfo;

    }

}

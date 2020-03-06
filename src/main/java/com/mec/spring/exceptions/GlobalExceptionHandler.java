package com.mec.spring.exceptions;

import com.mec.spring.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        logger.error("IOException handler executed");
    }
}

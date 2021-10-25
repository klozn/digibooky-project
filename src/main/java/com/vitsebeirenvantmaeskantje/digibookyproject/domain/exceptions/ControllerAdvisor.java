package com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerAdvisor {

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler
    public void handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        logger.error("Illegal Argument: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    public void handleUnauthorizedUserException(UnauthorizedUserException exception, HttpServletResponse response) throws IOException{
        logger.error("Unauthorized user: " + exception.getMessage());
        response.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }

    @ExceptionHandler
    public void handleUserNotFoundException(UserNotFoundException exception, HttpServletResponse response) throws IOException{
        logger.error("User not found: " + exception.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    public void handleBookIsDeletedException(BookIsDeletedException exception, HttpServletResponse response) throws IOException{
        logger.error("Requested book is deleted: " + exception.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}

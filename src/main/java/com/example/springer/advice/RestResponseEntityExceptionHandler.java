package com.example.springer.advice;

import com.example.springer.exceptions.BadRequestException;
import com.example.springer.exceptions.NewsArticleNotFoundException;
import com.example.springer.exceptions.WrongFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {WrongFieldError.class})
    protected ResponseEntity<Object> handleBadRequests(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Some parameters are not set";
        return handleExceptionInternal(ex, bodyOfResponse,
                                       new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = {BadRequestException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Bad Request";
        return handleExceptionInternal(ex, bodyOfResponse,
                                       new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = {NewsArticleNotFoundException.class})
    protected ResponseEntity<Object> handleNewsArticleNotFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "News Article not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                                       new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}

package com.example.springer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsArticleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5218143265247846948L;
    public NewsArticleNotFoundException(String message) {
        super(message);
    }
}

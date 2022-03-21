package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

/**
 * @program: springboot-blog-rest-api
 * @description: api exception
 * @author: Yaowen Hu
 * @create: 2022-03-21 15:38
 **/

public class BlogAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

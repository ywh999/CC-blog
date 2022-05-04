package com.springboot.blog.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: springboot-blog-rest-api
 * @description: error details
 * @author: Yaowen Hu
 * @create: 2022-04-14 12:14
 **/

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

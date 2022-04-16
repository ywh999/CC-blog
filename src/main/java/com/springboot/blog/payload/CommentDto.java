package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @program: springboot-blog-rest-api
 * @description: Comment Dto
 * @author: Yaowen Hu
 * @create: 2022-03-21 12:20
 **/

@Data
public class CommentDto {
    private long id;
    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be null or empty
    //email field validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    //comment body should not be null or empty
    //comment body must be minimum 10 characters
    @NotEmpty
    @Size(min = 10, message = "comment body must be minimum 10 characters")
    private String body;
}

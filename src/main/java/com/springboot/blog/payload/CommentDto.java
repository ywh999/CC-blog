package com.springboot.blog.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

@ApiModel(value = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private long id;

    @ApiModelProperty(value = "Comment name")
    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @ApiModelProperty(value = "Comment email")
    //email should not be null or empty
    //email field validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;


    @ApiModelProperty(value = "Comment body")
    //comment body should not be null or empty
    //comment body must be minimum 10 characters
    @NotEmpty
    @Size(min = 10, message = "comment body must be minimum 10 characters")
    private String body;
}

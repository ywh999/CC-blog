package com.springboot.blog.payload;

import lombok.Data;

/**
 * @program: springboot-blog-rest-api
 * @description: Comment Dto
 * @author: Yaowen Hu
 * @create: 2022-03-21 12:20
 **/

@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}

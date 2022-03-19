package com.springboot.blog.payload;

import lombok.Data;

/**
 * @program: springboot-blog-rest-api
 * @description: post data transefer object
 * @author: Yaowen Hu
 * @create: 2022-03-13 16:45
 **/

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
}

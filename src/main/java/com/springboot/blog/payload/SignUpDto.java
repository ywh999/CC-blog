package com.springboot.blog.payload;

import lombok.Data;

/**
 * @program: springboot-blog-rest-api
 * @description:
 * @author: Yaowen Hu
 * @create: 2022-05-03 10:37
 **/

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}

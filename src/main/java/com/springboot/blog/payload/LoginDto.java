package com.springboot.blog.payload;

import lombok.Data;

/**
 * @program: springboot-blog-rest-api
 * @description:
 * @author: Yaowen Hu
 * @create: 2022-05-03 10:05
 **/

//用户login的数据传输对象
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

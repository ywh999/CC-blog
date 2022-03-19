package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-blog-rest-api
 * @description: post controller
 * @author: Yaowen Hu
 * @create: 2022-03-19 14:06
 **/

@RestController//convert java object to json
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    //if only have an only constructor, we can omit the Autowire annotation
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post
    //request body annotation is to convert json to dto
    @PostMapping//定义处理方法的请求的url地址
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
}

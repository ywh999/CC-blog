package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    //get all posts rest api
    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    //get post by id api
    @GetMapping("/{id}")//placeholder and the annotation PathVariable is to save value in the placeholder
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        //ok is set status to ok and return a response entity
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
}

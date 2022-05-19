package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoV2;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-blog-rest-api
 * @description: post controller
 * @author: Yaowen Hu
 * @create: 2022-03-19 14:06
 **/

@Api(value = "CRUD Rest APIs for Post resources")
@RestController//convert java object to json
@RequestMapping()  //version api through uri
public class PostController {

    private PostService postService;

    //if only have an only constructor, we can omit the Autowire annotation
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')") //配置web security安全类后, 注解表示只用管理员可以访问
    //create blog post
    //request body annotation is to convert json to dto
    @PostMapping("/api/v1/posts")//定义处理方法的请求的url地址
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {   //valid 注解用来检查 PostDto里定义的规则
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get All Posts REST API")
    //get all posts rest api
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //get post by id api
    //version api by uri
//    @GetMapping("/api/v1/posts/{id}")//placeholder and the annotation PathVariable is to save value in the placeholder
//    @GetMapping(value = "/api/posts/{id}", params = "version=1")//version api by query parameter v1
//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")//version api by header v1
//    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.github.v1+json") // version api by content negotiation v1
    @ApiOperation(value = "Create Post By Id REST API")
    @GetMapping(value = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
        //ok is set status to ok and return a response entity
        return ResponseEntity.ok(postService.getPostById(id));
    }



//    @GetMapping("/api/v2/posts/{id}")//version api by uri v2
//    @GetMapping(value = "/api/posts/{id}", params = "version=2")//version api by query parameter v2
//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=2") // version api by header v2
//    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.github.v2+json")// version api by content negotiation v2
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Spring Boot");
//        tags.add("AWS");
//        postDtoV2.setTags(tags);
//        //ok is set status to ok and return a response entity
//        return ResponseEntity.ok(postDtoV2);
//    }


    @ApiOperation(value = "Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    //update post by id rest api
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,   //valid 注解用来检查 PostDto里定义的规则
                                              @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    //delete post rest api
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}

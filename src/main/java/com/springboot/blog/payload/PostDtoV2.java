package com.springboot.blog.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * @program: springboot-blog-rest-api
 * @description:
 * @author: Yaowen Hu
 * @create: 2022-05-05 16:48
 **/

@Data
public class PostDtoV2 {
    private long id;

    //title should not be null or empty
    //title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    //post description should be no null or empty
    //post description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    //post content should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private List<String> tags;
}

package com.springboot.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: springboot-blog-rest-api
 * @description: Post Entity of the blog system
 * @author: Yaowen Hu
 * @create: 2022-03-12 17:22
 **/


//实体类用getter和setter才不会报错, 因为避免了重写tostring, 如果null.tostring 就会报错
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    //级联所有操作，mappedby是用来指定由多的一方来维护关系, 避免生成中间表的, 用在一对多的关系里;
    // orphanRemoval是删除鼓励元素
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}

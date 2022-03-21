package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @program: springboot-blog-rest-api
 * @description: comment class
 * @author: Yaowen Hu
 * @create: 2022-03-21 11:21
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity//对实体的注解
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private long id;

    @Column
    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)// lazy模式, 只有用到的时候才将关联的post取出来放入内存中
    @JoinColumn(name = "post_id", nullable = false) //@JoinColumn 注解的作用：用来指定与所操作实体或实体集合相关联的数据库表中的列字段。
    //jointColumn写在主控方, 一般是多对1的多的一方, 多对多需要中间表
    private Post post;
}

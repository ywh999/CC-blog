package com.springboot.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: springboot-blog-rest-api
 * @description: Role class
 * @author: Yaowen Hu
 * @create: 2022-04-17 14:59
 **/

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 60)
    private String name;

}

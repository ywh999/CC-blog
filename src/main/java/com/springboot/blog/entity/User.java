package com.springboot.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @program: springboot-blog-rest-api
 * @description: user class
 * @author: Yaowen Hu
 * @create: 2022-04-17 14:56
 **/

@Data
@Entity
@Table(name = "users", uniqueConstraints = {          //自动生成
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //eager模式, 加载user的时候就将roles从数据库中取出来放入内存中 //级联所有操作
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),  // 多对多, jointable表示中间表名, joincolum reference表示该类主表外键, name表示该类主表外键在中间表的列名
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))  //inversejoincolum表示连接表副表外键 reference表示该主表外键, name表示该副表外键在中间表的列名
    private Set<Role> roles;
}

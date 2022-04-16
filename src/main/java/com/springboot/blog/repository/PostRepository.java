package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO layer, the JpaRepository do not need @repository annotation, because its father has this annotation
//and annotation is for class not interface
public interface PostRepository extends JpaRepository<Post, Long> {
//Spring JPA不是dao provider， 他底层用了Hibernate作为dao provider, spring jpa的主要作用是隐藏persistence layer, 避免重复代码
}

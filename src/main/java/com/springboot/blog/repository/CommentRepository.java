package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//reduce boilerplate code, prevent write dao class and method again and again
//一开始默认只有findById
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //带前缀的机制findXXBy,readAXXBy,queryXXBy,countXXBy, getXXBy自动解析的其余部分
    //通过动态代理实现
    List<Comment> findByPostId(long id);
}

package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long id); //带前缀的机制findXXBy,readAXXBy,queryXXBy,countXXBy, getXXBy自动解析的其余部分
    //通过动态代理实现
}

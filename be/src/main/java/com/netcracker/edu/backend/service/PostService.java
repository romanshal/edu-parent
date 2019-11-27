package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List <Post> findAll();
    Post findById(long id);
    Post save(Post Post);
    void delete(long id);
    Page <Post> findByUserId(Long userId, Pageable pageable);
    Post createPost(Long userId, Post post);
}

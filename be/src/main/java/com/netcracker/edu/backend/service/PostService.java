package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;
import java.util.List;

public interface PostService {

    List <Post> findAll();
    Post findById(long id);
    Post save(Post Post);
    void delete(long id);
}

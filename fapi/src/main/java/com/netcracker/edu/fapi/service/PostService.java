package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll();
    Post savePost(Post post);
    void deletePost(long id);
}

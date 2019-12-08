package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Post;

import java.io.File;
import java.util.List;

public interface PostService {

    List<Post> getAll();
    Post savePost(String description, String login);
    void deletePost(long id);
    File getPhoto(String fileName);
    Post getPostById(Long id);
    List<Post> getPostsByUserId(Long id);
}

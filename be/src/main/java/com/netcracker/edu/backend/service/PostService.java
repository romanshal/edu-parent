package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    List <Post> findAll(int page);
    Post findById(long id);
    Post save(Post post);
    void delete(long id);
    List <Post> findByUserId(int page,Long userId);
    String uploadFile(MultipartFile file);
    MultipartFile getFile(String fileName);
}

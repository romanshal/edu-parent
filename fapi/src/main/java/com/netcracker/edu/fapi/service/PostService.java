package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Post;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface PostService {

    List<Post> getAll();
    Post savePost(MultipartFile file, String description, String login);
    void deletePost(long id);
    void getFile(String fileName, HttpServletResponse response);
    Post getPostById(Long id);
    List<Post> getPostsByUserId(int page,Long id);
}

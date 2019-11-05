package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List <Post> findAll() {
        return (List<Post>) postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(long id) {
        postRepository.deleteById(id);
    }
}

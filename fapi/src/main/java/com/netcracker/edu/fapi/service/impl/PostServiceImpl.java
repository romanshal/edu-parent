package com.netcracker.edu.fapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostServiceImpl implements PostService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List <Post> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Post[] posts = restTemplate.getForObject(backendServerUrl + "/api/post", Post[].class);
        return posts == null ? Collections.emptyList() : Arrays.asList(posts);
    }

    @Override
    public Post savePost(Post post) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/post", post, Post.class).getBody();
    }

    @Override
    public void deletePost(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/post/" + id);
    }

//    @Override
//    public Post savePost(String description, String userId) {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        Post post = new Post(description,userId);
//        return restTemplate.postForEntity(backendServerUrl + "/api/cards", post, Post.class).getBody();
//    }
}

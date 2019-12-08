package com.netcracker.edu.fapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentServiceImpl implements CommentService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List <Comment> getAllComments() {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comments = restTemplate.getForObject(backendServerUrl + "/api/comment", Comment[].class);
        return comments == null ? Collections.emptyList() : Arrays.asList(comments);
    }

    @Override
    public Comment saveComment(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(backendServerUrl + "/api/comment", comment, Comment.class);
    }

    @Override
    public void deleteComment(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/comment/" + id);
    }

    @Override
    public List <Comment> getAllCommentsByPostId(Long postId) {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] comments = restTemplate.getForObject(backendServerUrl + "/api/comment/post/"+postId, Comment[].class);
        return comments == null ? Collections.emptyList() : Arrays.asList(comments);
    }
}

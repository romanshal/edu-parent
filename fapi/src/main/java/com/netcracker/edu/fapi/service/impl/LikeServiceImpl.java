package com.netcracker.edu.fapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Like;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.LikeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class LikeServiceImpl implements LikeService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Like addLike(Post postId, User userId) {
        Like like = new Like(postId, userId);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(backendServerUrl + "/api/like", like, Like.class);
    }
}

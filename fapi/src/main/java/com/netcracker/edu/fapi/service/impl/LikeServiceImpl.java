package com.netcracker.edu.fapi.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Like;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.LikeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class LikeServiceImpl implements LikeService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public void addLike(Long postId,Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        Like like = new Like();
       restTemplate.postForEntity(backendServerUrl + "api/like/post/"+postId+"/user/"+userId,like,Like.class).getBody();
    }

    @Override
    public List <Like> getLikesByPostId(Long postId) {
        RestTemplate restTemplate = new RestTemplate();
        Like[] likes = restTemplate.getForObject(backendServerUrl + "/api/like/post/" + postId, Like[].class);
        return likes == null ? Collections.emptyList() : Arrays.asList(likes);
    }
}

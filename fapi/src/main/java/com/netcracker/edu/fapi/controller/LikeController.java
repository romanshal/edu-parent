package com.netcracker.edu.fapi.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Like;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.LikeService;
import com.netcracker.edu.fapi.service.PostService;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(value = "/post/{postId}/user/{userId}")
    public void saveLike(@PathVariable(name = "postId") Long postId,
                         @PathVariable(name = "userId") Long userId) {
        likeService.addLike(postId, userId);
    }

    @GetMapping(value = "/post/{postId}")
    public List <Like> getLikesByPostId(@PathVariable(name = "postId") Long postId) {
        return likeService.getLikesByPostId(postId);
    }
}

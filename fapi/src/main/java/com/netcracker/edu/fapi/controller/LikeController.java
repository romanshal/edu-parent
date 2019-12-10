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

@RestController
@RequestMapping("/api/like")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping(value = "")
    public ResponseEntity <Like> saveLike(@RequestParam(required=false) String postId,
                                          @RequestParam(required=false) String userId) {
        User user=userService.findUserById(Long.valueOf(userId));
        Post post=postService.getPostById(Long.valueOf(postId));
        return ResponseEntity.ok(likeService.addLike(post,user));
    }
}

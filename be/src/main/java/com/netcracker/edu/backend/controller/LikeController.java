package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Like createLikeByUserIdAndPostId(@RequestBody Like like) {
        return likeService.addLike(like);
    }

    @RequestMapping(value = "/{postId}/{userId}", method = RequestMethod.POST)
    public void saveLike(@PathVariable(name = "postId") long postId, @PathVariable(name = "userId") long userId) {
        likeService.saveLike(postId, userId);
    }
}

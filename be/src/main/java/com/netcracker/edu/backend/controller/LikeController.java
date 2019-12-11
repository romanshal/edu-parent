package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "/post/{postId}/user/{userId}", method = RequestMethod.POST)
    public void saveLike(@PathVariable(name = "postId") long postId,
                         @PathVariable(name = "userId") long userId) {
        likeService.save(postId,userId);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity <List <Like>> getAllPosts() {
        List <Like> likes = this.likeService.findAll();

        if (likes.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity <List <Like>>(likes, HttpStatus.OK);
    }
}

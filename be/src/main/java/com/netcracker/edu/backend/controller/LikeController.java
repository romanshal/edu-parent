package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity <List <Like>> getAllLikes() {
        List <Like> likes = this.likeService.findAll();

        if(likes.isEmpty()){
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity <List <Like>>(likes, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public  ResponseEntity <List <Like>> getLikesByPostId(@PathVariable(name = "postId") long postId) {
        List <Like> likes = this.likeService.getByPostId(postId);
        if(likes.isEmpty()){
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity <List <Like>>(likes, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{postId}/user/{userId}", method = RequestMethod.POST)
    public void addOrDeleteLikeByPostIdAndUserId(@PathVariable(name = "postId") long postId,
                                                 @PathVariable(name = "userId") long userId) {
        Optional <Like> foundLike = likeService.getByPostIdAndUserId(postId, userId);
        if(foundLike.isPresent()){
            likeService.deleteLike(postId,userId);
        } else {
            likeService.save(postId, userId);
        }
    }
}

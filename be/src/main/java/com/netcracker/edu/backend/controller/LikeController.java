package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @GetMapping("/post/{postId}")
    public long getAllPostsByUserId(@PathVariable(value = "postId") Long postId) {
        return likeService.countByPostId(postId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Like addLike(@Valid @RequestBody Like like) {
        return likeService.addLike(like);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity <List <Like>> getAllPosts(){
        List<Like> likes=this.likeService.findAll();

//        if (posts.isEmpty()){
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<List<Like>>(likes, HttpStatus.OK);
    }
}

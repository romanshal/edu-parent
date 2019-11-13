package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity <List <Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

    @PostMapping
    public ResponseEntity<Post> saveCard(@RequestBody Post post) {
        if (post != null) {
            return ResponseEntity.ok(postService.savePost(post));
        } return null;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCard(@PathVariable long id){
        postService.deletePost(id);
    }
}
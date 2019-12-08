package com.netcracker.edu.fapi.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.UIModels.UIPost;
import com.netcracker.edu.fapi.converter.PostToPostUIModel;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostToPostUIModel postToPostUIModel;

    @GetMapping
    public ResponseEntity <List <UIPost>> getAllPosts() {
        return ResponseEntity.ok(postService.getAll().stream().map(postToPostUIModel::convert).collect(Collectors.toList()));
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity <List <UIPost>> getAllPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUserId(userId).stream().map(postToPostUIModel::convert).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <UIPost> getPostById(@PathVariable Long id ) {
        return ResponseEntity.ok(postToPostUIModel.convert(postService.getPostById(id)));
    }

    @PostMapping
    public ResponseEntity <Post> savePost(@RequestParam String description,
                                          @RequestParam String login) {
        return ResponseEntity.ok(postService.savePost(description, login));

    }

//    @PostMapping
//    public ResponseEntity<Post> savePost(@ModelAttribute Post post) {
//        if (post != null) {
//
//            return ResponseEntity.ok(postService.savePost(post));
//        } return null;
//    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}

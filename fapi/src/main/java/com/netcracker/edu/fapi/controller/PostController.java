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

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity <List <UIPost>> getAllPostsByUserId(@PathVariable Long userId,
                                                              @RequestParam int page) {
        return ResponseEntity.ok(postService.getPostsByUserId(page,userId).stream().map(postToPostUIModel::convert).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <UIPost> getPostById(@PathVariable Long id ) {
        return ResponseEntity.ok(postToPostUIModel.convert(postService.getPostById(id)));
    }

    @PostMapping
    public ResponseEntity <Post> savePost(@RequestParam MultipartFile file,
                                          @RequestParam String description,
                                          @RequestParam String login) {
        return ResponseEntity.ok(postService.savePost(file,description, login));

    }

    @GetMapping(value = "/getFile/{fileName}")
    public void getFile(@PathVariable String fileName, HttpServletResponse response) {
        postService.getFile(fileName, response);
        response.setContentType("image/jpeg");
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

package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.PostService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List <Post> getAllPostsByUserId(@PathVariable(value = "userId") Long userId,
                                           @RequestParam int page) {
        return postService.findByUserId(page, userId);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public Post createPostByUserId(@PathVariable(value = "userId") Long userId,
                                   @RequestBody Post post) {
        return postService.createPost(userId, post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Post> getPostById(@PathVariable("id") Long postId) {

        Post post = this.postService.findById(postId);

        if(post == null){
            return new ResponseEntity <Post>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity <Post>(post, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity <Post> createPost(@RequestBody Post post) {

        if(post == null){
            return new ResponseEntity <Post>(HttpStatus.BAD_REQUEST);
        }

        this.postService.save(post);
        return new ResponseEntity <Post>(post, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity <Post> deletePost(@PathVariable("id") Long id) {
        Post post = this.postService.findById(id);

        this.postService.delete(id);
        return new ResponseEntity <Post>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity <List <Post>> getAllPosts(@RequestParam int page) {
        List <Post> posts = this.postService.findAll(page);

//        if (posts.isEmpty()){
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity <List <Post>>(posts, HttpStatus.OK);
    }
}

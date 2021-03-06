package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.service.CommentService;
import com.netcracker.edu.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity <List <Comment>> getAllComments() {
        List <Comment> comments = this.commentService.findAll();

//        if (posts.isEmpty()){
        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity <List <Comment>>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Comment> getCommentById(@PathVariable(value = "id") Long postId,
                                                   @PathVariable("id") Long commentId) {

        Comment comment = this.commentService.findById(commentId);

        if(comment == null){
            return new ResponseEntity <Comment>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity <Comment>(comment, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{postId}/user/{userId}", method = RequestMethod.POST)
    public ResponseEntity <Comment> createComment(@RequestBody Comment comment,
                                                  @PathVariable(value = "postId") Long postId,
                                                  @PathVariable(value = "userId") Long userId) {

        if(comment == null){
            return new ResponseEntity <Comment>(HttpStatus.BAD_REQUEST);
        }

        this.commentService.save(comment,postId,userId);
        return new ResponseEntity <Comment>(comment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity <Comment> deleteCommentById(@PathVariable("id") Long id) {
        Comment comment = this.commentService.findById(id);

        if(comment == null){
            return new ResponseEntity <Comment>(HttpStatus.NOT_FOUND);
        }

        this.commentService.delete(id);
        return new ResponseEntity <Comment>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity <Comment> deleteAllCommentsByPostId(@PathVariable("postId") Long postId) {
        this.commentService.deleteAll(postId);
        return new ResponseEntity <Comment>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/post/{postId}",method = RequestMethod.GET)
    public Iterable<Comment> getCommentsByCardId(@PathVariable long postId) {
        return commentService.findByPostId(postId);
    }


}

package com.netcracker.edu.fapi.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.UIModels.UIComment;
import com.netcracker.edu.fapi.converter.CommentToCommentUIModel;
import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentToCommentUIModel commentToCommentUIModel;

    @GetMapping
    public ResponseEntity <List <UIComment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments().stream().map(commentToCommentUIModel::convert).collect(Collectors.toList()));
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity <List <UIComment>> getAllCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId).stream().map(commentToCommentUIModel::convert).collect(Collectors.toList()));
    }

    @PostMapping(value = "/post/{postId}/user/{userId}")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment,
                                               @PathVariable Long postId,
                                               @PathVariable Long userId) {
        return ResponseEntity.ok(commentService.saveComment(comment,postId,userId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
    }
}

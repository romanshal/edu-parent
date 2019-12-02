package com.netcracker.edu.fapi.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity <List <Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@ModelAttribute Comment comment) {
        if (comment != null) {
            return ResponseEntity.ok(commentService.saveComment(comment));
        } return null;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable long id){
        commentService.deleteComment(id);
    }
}

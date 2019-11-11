package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <Comment> getComment(@PathVariable (value = "id") Long postId,
                                                 @PathVariable("id") Long commentId){

        Comment comment=this.commentService.findById(commentId);

        if(comment == null){
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Comment> createPost(@RequestBody @Valid Comment comment){

        if(comment==null){
            return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
        }

        this.commentService.save(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id){
        Comment comment=this.commentService.findById(id);

        if(comment==null){
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        this.commentService.delete(id);
        return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
    }


}

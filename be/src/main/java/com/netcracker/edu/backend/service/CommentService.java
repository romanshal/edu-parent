package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

import java.util.List;

public interface CommentService {

    List <Comment> findAll();
    Comment findById(long id);
    Comment save(Comment comment);
    void delete(long id);
//    List <Comment> findAllCommentsByPostId(Long postId);
}

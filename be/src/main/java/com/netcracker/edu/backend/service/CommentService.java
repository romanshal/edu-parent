package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

import java.util.List;

public interface CommentService {

    List <Comment> findAll();
    Comment findById(long id);
    Comment save(Comment comment,Long postId,long userId);
    void delete(long id);
    void deleteAll(long postId);
//    List <Comment> findAllCommentsByPostId(Long postId);
}

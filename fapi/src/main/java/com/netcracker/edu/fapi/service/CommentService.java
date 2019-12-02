package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Comment;

import java.util.List;

public interface CommentService {

    List <Comment> getAllComments();
    Comment saveComment(Comment comment);
    void deleteComment(long id);
}

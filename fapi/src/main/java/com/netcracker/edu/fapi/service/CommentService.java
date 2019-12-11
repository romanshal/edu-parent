package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Comment;

import java.util.List;

public interface CommentService {

    List <Comment> getAllComments();
    Comment saveComment(String content,Long postId,Long userId);
    void deleteComment(long id);
    List<Comment> getAllCommentsByPostId(Long postId);
}

package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.entity.Post;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.repository.PostRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List <Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment,Long postId,long userId) {
        comment.setPost(postRepository.findById(postId).get());
        comment.setUser(userRepository.findById(userId));
        return commentRepository.save(comment);
    }

    @Override
    public void delete(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAll(long postId) {
        commentRepository.deleteAll(postId);
    }

//    @Override
//    public List <Comment> findAllCommentsByPostId(Long postId) {
//        return commentRepository.findAllCommentsByPostId(postId);
//    }


}

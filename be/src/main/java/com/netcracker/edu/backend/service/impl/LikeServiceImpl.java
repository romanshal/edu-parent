package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.repository.LikeRepository;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;


    @Override
    public List <Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public void save(long postId, long userId) {
        likeRepository.saveByPostId(postId, userId);
    }

    @Override
    public Optional <Like> getByPostIdAndUserId(long postId, long userId) {
        return likeRepository.getByPostIdAndUserId(postId, userId);
    }

    @Override
    public void deleteLike(long postId, long userId) {
        likeRepository.deleteLike(postId, userId);
    }

    @Override
    public void deleteAllLikes(long postId) {
        likeRepository.deleteAllLikes(postId);
    }
}

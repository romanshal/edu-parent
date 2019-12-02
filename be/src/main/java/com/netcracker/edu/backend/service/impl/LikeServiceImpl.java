package com.netcracker.edu.backend.service.impl;
import com.netcracker.edu.backend.entity.Like;
import com.netcracker.edu.backend.repository.LikeRepository;
import com.netcracker.edu.backend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;


    @Override
    public long countByPostId(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    @Override
    public Like addLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public List <Like> findAll() {
        return likeRepository.findAll();
    }
}

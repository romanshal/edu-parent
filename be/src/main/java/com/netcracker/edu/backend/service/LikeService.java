package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Like;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikeService {

    long countByPostId(Long postId);
    Like addLike(Like like);
    List <Like> findAll();
}

package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Like;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LikeService {

    List <Like> findAll();

    void save(long postId,long userId);
}

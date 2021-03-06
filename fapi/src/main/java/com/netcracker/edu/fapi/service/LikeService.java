package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Like;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;

import java.util.List;

public interface LikeService {

    void addLike(Long postId,Long userId);
    List <Like> getLikesByPostId(Long postId);
}

package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Like;
import com.netcracker.edu.fapi.models.Post;
import com.netcracker.edu.fapi.models.User;

public interface LikeService {

    Like addLike(Post postId, User userId);
}
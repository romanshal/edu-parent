package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Like;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LikeService {

    List <Like> findAll();

    void save(long postId,long userId);

    List <Like> getByPostId(long postId);

    void deleteLike(long postId, long userId);

    void deleteAllLikes(long postId);

    Optional <Like> getByPostIdAndUserId(long postId, long userId);
}

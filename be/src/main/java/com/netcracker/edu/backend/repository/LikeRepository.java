package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    long countByPostId(Long postId);
}

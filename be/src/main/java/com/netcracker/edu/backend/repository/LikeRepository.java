package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    long countByPostId(Long postId);

    @Modifying
    @Transactional
    @Query(value = "insert into likes(post_id,user_id) values (:postId,:userId)", nativeQuery = true)
    void saveLike(@Param("postId") long postId, @Param("userId") long userId);
}

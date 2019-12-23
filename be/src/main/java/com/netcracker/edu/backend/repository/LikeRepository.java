package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Like;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into likes(post_id,user_id) values (:postId,:userId)", nativeQuery = true)
    void saveByPostId(@Param("postId")Long postId,@Param("userId")Long userId);

    @Transactional
    @Query(value = "select * from likes where post_id=:postId", nativeQuery = true)
    List <Like> getByPostId(@Param("postId")Long postId);

    @Transactional
    @Query(value = "select * from likes where post_id=:postId and user_id=:userId", nativeQuery = true)
    Optional<Like> getByPostIdAndUserId(@Param("postId")Long postId, @Param("userId")Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from likes where post_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteLike(@Param("postId") long postId, @Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from likes where post_id=:postId", nativeQuery = true)
    void deleteAllLikes(@Param("postId") Long postId);

}

package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {

    Comment findById(long id);

    Iterable<Comment> findByPostId(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from comment where post_id=:postId", nativeQuery = true)
    void deleteAll(@Param("postId") Long postId);
    //    Page <Comment> findByPostId(Long postId, Pageable pageable);
//    List <Comment> findAllCommentsByPostId(Long postId);
}

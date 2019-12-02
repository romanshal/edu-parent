package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {

    Comment findById(long id);
    Page <Comment> findByPostId(Long postId, Pageable pageable);
}

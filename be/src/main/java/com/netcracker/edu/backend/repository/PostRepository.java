package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {

    Post findById(long id);
    Page <Post> findByUserId(Long userId, Pageable pageable);
}

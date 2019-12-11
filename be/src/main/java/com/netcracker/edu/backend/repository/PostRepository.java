package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Long> {

    Post findById(long id);
    List <Post> findByUserId(Long userId);
    Page <Post> findByUserId(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,Long userId);
    Page <Post> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable);
}

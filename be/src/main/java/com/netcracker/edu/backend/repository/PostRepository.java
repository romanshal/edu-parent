package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository <Post, Long> {

    Post findById(long id);
}

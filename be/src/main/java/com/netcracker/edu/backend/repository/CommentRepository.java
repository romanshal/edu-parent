package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Long> {

    Comment findById(long id);
}

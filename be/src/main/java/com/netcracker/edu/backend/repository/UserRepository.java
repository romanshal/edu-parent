package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findById(long id);

    User findByLogin(String login);

    //in repo custom sql
}

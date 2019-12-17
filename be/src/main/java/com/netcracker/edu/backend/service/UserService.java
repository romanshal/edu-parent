package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(long id);
    User save(User user);
    void delete(long id);
    User findByLogin(String login);
    void subscribe(long userId,long friendId);
    void unsubscribe(long userId, long friendId);
}

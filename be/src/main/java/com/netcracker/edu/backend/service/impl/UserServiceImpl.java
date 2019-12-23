package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List <User> findAll() {
        return (List <User>) userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setRole(new Role(2));
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void subscription(long userId, long friendId) {
        Optional <Long> user = userRepository.checkSubscription(userId, friendId);
        if(user.isPresent()){
            userRepository.unsubscription(userId, friendId);
        } else {
            userRepository.subscription(userId, friendId);
        }
    }
}

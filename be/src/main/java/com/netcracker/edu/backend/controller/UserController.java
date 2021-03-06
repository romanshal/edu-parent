package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <User> getUserById(@PathVariable("id") Long userId) {

        User user = this.userService.findById(userId);

        if(user == null){
            return new ResponseEntity <User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity <User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List <User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity <User> createUser(@RequestBody User user) {

        if(user == null){
            return new ResponseEntity <User>(HttpStatus.BAD_REQUEST);
        }

        this.userService.save(user);
        return new ResponseEntity <User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity <User> deleteUser(@PathVariable("id") Long id) {
        User user = this.userService.findById(id);

        if(user == null){
            return new ResponseEntity <User>(HttpStatus.NOT_FOUND);
        }

        this.userService.delete(id);
        return new ResponseEntity <User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public ResponseEntity <User> getUserByLogin(@PathVariable(name = "login") String login) {
        User user = userService.findByLogin(login);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/subscribe/user/{userId}", method = RequestMethod.POST)
    public void subscription(@RequestBody User friend,
                             @PathVariable(name = "userId") Long userId) {
        this.userService.subscription(userId, friend.getId());
    }
}

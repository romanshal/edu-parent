package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity <User> getUser(@PathVariable("id") Long userId){

        User user=this.userService.findById(userId);

        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){

        if(user==null){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

        this.userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        User user=this.userService.findById(id);

        if(user==null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        this.userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long userId){
        User updatedUser = this.userService.findById(userId);

        if(updatedUser == null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        updatedUser.setAge(user.getAge());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setFriendOf(user.getFriendOf());
        updatedUser.setFriends(user.getFriends());

        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

    }
}

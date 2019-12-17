package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.UIModels.UIUser;
import com.netcracker.edu.fapi.converter.UserToUserUIModel;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserUIModel userToUserUIModel;

    @PreAuthorize("hasRole('admin')")
    @GetMapping
    public List<UIUser> getAllUsers(){
        return userService.findAll().stream().map(userToUserUIModel::convert).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UIUser getUserById(@PathVariable Long id) {
        return userToUserUIModel.convert(userService.findUserById(id));
    }

    @GetMapping("/login/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/current")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByLogin(((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername());
    }

    @RequestMapping(value="/subscribe/user/{userId}", method = RequestMethod.POST, produces = "application/json")
    public void subscribe(@RequestBody User friend,
                          @PathVariable Long userId){
        userService.subscribe(userId,friend);
    }
}

package com.netcracker.edu.fapi.UIModels;

import com.netcracker.edu.fapi.models.Role;
import com.netcracker.edu.fapi.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UIUser {

    private Long id;
    private String login;
    private String role;
    private List <User> friends = new ArrayList <>();
    private List <User> friendOf = new ArrayList <>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List <User> getFriends() {
        return friends;
    }

    public void setFriends(List <User> friends) {
        this.friends = friends;
    }

    public List <User> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(List <User> friendOf) {
        this.friendOf = friendOf;
    }
}

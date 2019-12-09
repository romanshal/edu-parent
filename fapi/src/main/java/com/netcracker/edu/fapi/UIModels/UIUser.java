package com.netcracker.edu.fapi.UIModels;

import com.netcracker.edu.fapi.models.Role;

import java.util.Objects;

public class UIUser {

    private Long id;
    private String login;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

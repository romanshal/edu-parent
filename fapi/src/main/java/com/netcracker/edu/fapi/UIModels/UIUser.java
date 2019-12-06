package com.netcracker.edu.fapi.UIModels;

import com.netcracker.edu.fapi.models.Role;

import java.util.Objects;

public class UIUser {

    private Long id;
    private String name;
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        UIUser uiUser = (UIUser) o;
        return getId().equals(uiUser.getId()) &&
                getName().equals(uiUser.getName()) &&
                getRole().equals(uiUser.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole());
    }

    @Override
    public String toString() {
        return "UIUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}

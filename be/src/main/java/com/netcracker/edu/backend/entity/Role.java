package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "role_name")
    private String roleName;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return getId() == role.getId() &&
                Objects.equals(getRoleName(), role.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    private int id;
    private String roleName;
    //private Set <User> users = new HashSet <>();

    @Id
    @Column(name = "role_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


//    @OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY,
//            cascade = {CascadeType.REMOVE})
//    public Set <User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set <User> users) {
//        this.users = users;
//    }


}

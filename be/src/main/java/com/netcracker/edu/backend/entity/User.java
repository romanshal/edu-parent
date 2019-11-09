package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private Long id;
    private String login;
    private String password;
    private String email;
    private int age;
    private Set <Post> posts = new HashSet <>();
    private Set <Comment> comments = new HashSet <>();
    private Role role;
    private Set<User> friends;
    private Set<User> friendOf;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonIgnore
    @OneToMany(targetEntity = Post.class, mappedBy = "user", fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE})
    public Set <Post> getPosts() {
        return posts;
    }

    public void setPosts(Set <Post> posts) {
        this.posts = posts;
    }

    @OneToMany(targetEntity = Comment.class, mappedBy = "user", fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE})
    public Set <Comment> getComments() {
        return comments;
    }

    public void setComments(Set <Comment> comments) {
        this.comments = comments;
    }

    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="userId"),
            inverseJoinColumns=@JoinColumn(name="friendId")
    )
    public Set <User> getFriends() {
        return friends;
    }

    public void setFriends(Set <User> friends) {
        this.friends = friends;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="tbl_friends",
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="userId")
    )
    public Set <User> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(Set <User> friendOf) {
        this.friendOf = friendOf;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getAge() == user.getAge() &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                getEmail().equals(user.getEmail()) &&
                Objects.equals(getPosts(), user.getPosts()) &&
                Objects.equals(getComments(), user.getComments()) &&
                getRole().equals(user.getRole()) &&
                Objects.equals(getFriends(), user.getFriends()) &&
                Objects.equals(getFriendOf(), user.getFriendOf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getEmail(), getAge(), getPosts(), getComments(), getRole(), getFriends(), getFriendOf());
    }
}

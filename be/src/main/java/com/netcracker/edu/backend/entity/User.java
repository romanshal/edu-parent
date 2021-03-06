package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List <Post> posts = new ArrayList <>();

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_friends",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "friendId")
    )
    private List <User> friends = new ArrayList <>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_friends",
            joinColumns = @JoinColumn(name = "friendId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List <User> friendOf = new ArrayList <>();

    private boolean block = false;

    public User() {
    }

    public User(Long id, String login, String password, String email, int age, List <Post> posts, Role role, List <User> friends, List <User> friendOf) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
        this.posts = posts;
//        this.comments = comments;
        this.role = role;
        this.friends = friends;
        this.friendOf = friendOf;
    }


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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public List <Post> getPosts() {
        return posts;
    }

    public void setPosts(List <Post> posts) {
        this.posts = posts;
    }

////    @JsonManagedReference
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
//            cascade = {CascadeType.REMOVE})
//    public List <Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List <Comment> comments) {
//        this.comments = comments;
//    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public List <User> getFriends() {
        return friends.stream().map(this::conv).collect(Collectors.toList());
    }

    public void setFriends(List <User> friends) {
        this.friends = friends;
    }


    public List <User> getFriendOf() {

        return friendOf.stream().map(this::conv).collect(Collectors.toList());
    }

    public void setFriendOf(List <User> friendOf) {

        this.friendOf = friendOf;
    }

    //    @Column(name = "status")
    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                isBlock() == user.isBlock() &&
                getId().equals(user.getId()) &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPosts(), user.getPosts()) &&
                Objects.equals(getRole(), user.getRole());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getEmail(), getAge(), getPosts(), getRole(), isBlock());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", posts=" + posts +
                ", role=" + role +
                ", friends=" + friends +
                ", friendOf=" + friendOf +
                ", block=" + block +
                '}';
    }

    public User conv(User user) {
        User userDTO = new User();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }
}

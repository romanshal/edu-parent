package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment implements Serializable {

    private Long id;
    private String content;
    private Post post;
    private User user;
    @NotNull(message = "Time creation is mandatory")
    private Timestamp timeCreation;

    public Comment(Long id, String content, Post post, User user) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
    }
}

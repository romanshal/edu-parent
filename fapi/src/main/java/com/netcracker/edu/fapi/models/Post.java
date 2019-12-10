package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable {

    private Long id;
    private Set <Object> tags = new HashSet <>();
    private List<Comment> comments= new ArrayList <>();
    private String description;
    private String filename;
    private String login;
    private MultipartFile file;
    private User user;
    private Set <Like> likes = new HashSet();

    public Post(String description, User user) {
        this.description=description;
        this.user=user;
    }

    public Post(Long id, Set <Object> tags, String description, User user, Set <Like> likes) {
        this.id = id;
        this.tags = tags;
        this.description = description;
        this.user = user;
        this.likes = likes;
    }

    public Post() {
    }

    public Post(String description, String filename, User user) {
        this.description=description;
        this.filename=filename;
        this.user=user;
    }

    public List <Comment> getComments() {
        return comments;
    }

    public void setComments(List <Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set <Object> getTags() {
        return tags;
    }

    public void setTags(Set <Object> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set <Like> getLikes() {
        return likes;
    }

    public void setLikes(Set <Like> likes) {
        this.likes = likes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

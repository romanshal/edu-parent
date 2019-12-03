package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post implements Serializable{

    private Long id;
    private Set <Object> tags = new HashSet <>();
    private String description;
    private User user;

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

    public Set<Object> getTags() {
        return tags;
    }

    public void setTags(Set<Object> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) &&
                Objects.equals(getTags(), post.getTags()) &&
                Objects.equals(getDescription(), post.getDescription()) &&
                Objects.equals(getUser(), post.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTags(), getDescription(), getUser());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", tags=" + tags +
                ", description='" + description + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}

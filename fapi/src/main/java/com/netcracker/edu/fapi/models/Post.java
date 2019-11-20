package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private Long id;
    private String[] tags;
    private String description;

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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getId().equals(post.getId()) &&
                Arrays.equals(getTags(), post.getTags()) &&
                Objects.equals(getDescription(), post.getDescription());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getDescription());
        result = 31 * result + Arrays.hashCode(getTags());
        return result;
    }
}

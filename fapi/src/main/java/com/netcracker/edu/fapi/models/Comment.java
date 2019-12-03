package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment implements Serializable {

    private int id;
    private String content;
    private Post post;

    public Comment(int id, String content, Post post) {
        this.id = id;
        this.content = content;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return getId() == comment.getId() &&
                Objects.equals(getContent(), comment.getContent()) &&
                Objects.equals(getPost(), comment.getPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getPost());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", post=" + post +
                '}';
    }
}

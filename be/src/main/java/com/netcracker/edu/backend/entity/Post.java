package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "post")
public class Post implements Serializable {

    private Long id;
    private User user;
    private String description;
    private Set<Comment> comments = new HashSet<>();
    private Set <Tag> tags = new HashSet <>();
    private Calendar data;

    public Post(){
    }

    public Post(Long id, User user, String description, Set <Comment> comments, Set <Tag> tags) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.comments = comments;
        this.tags = tags;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "user")
    @Column(name = "comments")
    public Set <Comment> getComments() {
        return comments;
    }

    public void setComments(Set <Comment> comments) {
        this.comments = comments;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "tag_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    public Set <Tag> getTags() {
        return tags;
    }

    public void setTags(Set <Tag> tags) {
        this.tags = tags;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getId() == post.getId() &&
                getUser().equals(post.getUser()) &&
                Objects.equals(getDescription(), post.getDescription()) &&
                Objects.equals(getComments(), post.getComments()) &&
                Objects.equals(getTags(), post.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDescription(), getComments(), getTags());
    }
}

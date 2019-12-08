package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "post")
public class Post implements Serializable {

    private Long id;
    private User user;
    private String description;
    private List <Comment> comments = new ArrayList<>();
    private List <Like> likes = new ArrayList();
    private Set <Tag> tags = new HashSet <>();

    public Post() {
    }

    public Post(Long id, User user, String description, Set <Tag> tags) {
        this.id = id;
        this.user = user;
        this.description = description;
//        this.comments = comments;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "comments")
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name ="comments_post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    public List <Comment> getComments() {
        return comments;
    }

    public void setComments(List <Comment> comments) {
        this.comments = comments;
    }

    @Column(name = "tags")
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

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    public List <Like> getLikes() {
        return likes;
    }

    public void setLikes(List <Like> likes) {
        this.likes = likes;
    }

}

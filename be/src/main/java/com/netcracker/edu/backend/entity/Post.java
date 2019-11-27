package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "post")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Post implements Serializable {

    private Long id;
    private User user;
    private String description;
    private List <Comment> comments = new ArrayList<>();
    private Set <Tag> tags = new HashSet <>();

    public Post(){
    }

    public Post(Long id, User user, String description, List <Comment> comments, Set <Tag> tags) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.comments = comments;
        this.tags = tags;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @Column(name = "comments")
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
    public Set<Tag> getTags() {
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
        return getId().equals(post.getId()) &&
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

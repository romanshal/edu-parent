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
    private String filename;

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
    @OneToMany(mappedBy = "post")
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

    @Column
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) &&
                Objects.equals(getUser(), post.getUser()) &&
                Objects.equals(getDescription(), post.getDescription()) &&
                Objects.equals(getComments(), post.getComments()) &&
                Objects.equals(getLikes(), post.getLikes()) &&
                Objects.equals(getTags(), post.getTags()) &&
                Objects.equals(filename, post.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDescription(), getComments(), getLikes(), getTags(), filename);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                ", tags=" + tags +
                ", filename='" + filename + '\'' +
                '}';
    }
}

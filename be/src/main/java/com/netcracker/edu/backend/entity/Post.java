package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "comments")
    @OneToMany(mappedBy = "post")
    private List <Comment> comments = new ArrayList <>();

    @OneToMany(mappedBy = "post")
    private List <Like> likes = new ArrayList();

    @Column
    private String filename;

    @Column(name = "time_creation")
    private Timestamp timeCreation;

    public Post() {
    }

    public Post(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List <Comment> getComments() {
        return comments;
    }

    public void setComments(List <Comment> comments) {
        this.comments = comments;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List <Like> getLikes() {
        return likes.stream().map(this::conv).collect(Collectors.toList());
    }

    public void setLikes(List <Like> likes) {
        this.likes = likes;
    }


    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
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
                Objects.equals(filename, post.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDescription(), getComments(), getLikes(), filename);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                ", filename='" + filename + '\'' +
                '}';
    }

    public Like conv(Like like) {
        Like likeDTO = new Like();
        return likeDTO;
    }
}

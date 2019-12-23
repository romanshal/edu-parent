package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content")
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "time_creation")
    private Timestamp timeCreation;

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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getContent(), comment.getContent()) &&
                Objects.equals(getPost(), comment.getPost()) &&
                Objects.equals(getUser(), comment.getUser()) &&
                Objects.equals(getTimeCreation(), comment.getTimeCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getPost(), getUser(), getTimeCreation());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", post=" + post +
                ", user=" + user +
                ", timeCreation=" + timeCreation +
                '}';
    }
}

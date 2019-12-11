package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "likes")
@IdClass(value = Like.class)
public class Like implements Serializable {

    private Post post;
    private User user;

    public Like() {
    }
    
    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

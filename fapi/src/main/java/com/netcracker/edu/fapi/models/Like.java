package com.netcracker.edu.fapi.models;

import java.util.Objects;

public class Like {

   private Long id;
   private User user;
   private Post post;

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
      Like like = (Like) o;
      return Objects.equals(getId(), like.getId()) &&
              Objects.equals(getUser(), like.getUser()) &&
              Objects.equals(getPost(), like.getPost());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getUser(), getPost());
   }

   @Override
   public String toString() {
      return "Like{" +
              "id=" + id +
              ", user=" + user +
              ", post=" + post +
              '}';
   }
}

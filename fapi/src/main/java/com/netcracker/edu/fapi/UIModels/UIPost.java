package com.netcracker.edu.fapi.UIModels;

import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.models.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UIPost {

    private Long id;
    private String description;
    private List<UIComment> uiComments= new ArrayList <>();
    private String tags;
    private String userLogin;
    private String filename;
    private int countLike;
    private Long userId;
    @NotNull(message = "Time creation is mandatory")
    private Timestamp timeCreation;

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

    public List <UIComment> getUiComments() {
        return uiComments;
    }

    public void setUiComments(List <UIComment> uiComments) {
        this.uiComments = uiComments;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
    }
}

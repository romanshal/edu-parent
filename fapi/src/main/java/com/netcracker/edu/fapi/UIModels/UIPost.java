package com.netcracker.edu.fapi.UIModels;

import com.netcracker.edu.fapi.models.Comment;
import com.netcracker.edu.fapi.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UIPost {

    private Long id;
    private String description;
    private List<UIComment> uiComments= new ArrayList <>();
    private String tags;
    private UIUser uiUser;
    private String filePath;
    private int countLike;

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

    public UIUser getUiUser() {
        return uiUser;
    }

    public void setUiUser(UIUser uiUser) {
        this.uiUser = uiUser;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

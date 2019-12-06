package com.netcracker.edu.fapi.UIModels;

import com.netcracker.edu.fapi.models.User;

import java.util.HashSet;
import java.util.Set;

public class UIPost {

    private String description;
    private String tags;
    private UIUser uiUser;
    private String filePath;
    private int countLike;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

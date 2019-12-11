package com.netcracker.edu.fapi.UIModels;

public class UIComment {

    private Long id;
    private String content;
    private UIUser uiUser;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UIUser getUiUser() {
        return uiUser;
    }

    public void setUiUser(UIUser uiUser) {
        this.uiUser = uiUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.netcracker.edu.fapi.UIModels;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class UIComment {

    private Long id;
    private String content;
    private UIUser uiUser;
    @NotNull(message = "Time creation is mandatory")
    private Timestamp timeCreation;

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

    public Timestamp getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(Timestamp timeCreation) {
        this.timeCreation = timeCreation;
    }
}

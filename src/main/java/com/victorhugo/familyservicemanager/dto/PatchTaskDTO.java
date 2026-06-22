package com.victorhugo.familyservicemanager.dto;

public class PatchTaskDTO {

    private String description;
    private Long userId;

    //methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

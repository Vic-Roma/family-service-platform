package com.victorhugo.familyservicemanager.dto;

public class TaskRequestDTO {

    //fields
    private String description;
    private Long userId;

    //Constructors
    public TaskRequestDTO(){}
    public TaskRequestDTO(String description, Long userId){
        this.description = description;
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

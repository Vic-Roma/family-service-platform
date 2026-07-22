package com.victorhugo.familyservicemanager.dto;

import com.victorhugo.familyservicemanager.enums.TaskStatus;

public class TaskDetailsDTO {

    //fields
    private Long id;
    private String description;
    private UserSummaryDTO userSummaryDTO;
    private TaskStatus status;

    //Constructors
    public TaskDetailsDTO(){}
    public TaskDetailsDTO(Long id,String description,UserSummaryDTO userSummaryDTO, TaskStatus status){
        this.id = id;
        this.description = description;
        this.userSummaryDTO = userSummaryDTO;
        this.status = status;
    }

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

    public UserSummaryDTO getUserSummaryDTO() {
        return userSummaryDTO;
    }

    public void setUserSummaryDTO(UserSummaryDTO userSummaryDTO) {
        this.userSummaryDTO = userSummaryDTO;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

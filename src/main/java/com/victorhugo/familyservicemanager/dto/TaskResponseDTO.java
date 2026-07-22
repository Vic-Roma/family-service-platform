package com.victorhugo.familyservicemanager.dto;


//Este Dto se usara para regregar unicamente id, description y user_id, no el user completo,
// de esta forma el json regresado sera mas plano y menos complejo

import com.victorhugo.familyservicemanager.enums.TaskStatus;

public class TaskResponseDTO {

    //fields
    private Long id;
    private String description;
    private Long userId;
    private TaskStatus status;

    public TaskResponseDTO(){}

    public TaskResponseDTO(Long id, String description, Long userId, TaskStatus status){
        this.id = id;
        this.description = description;
        this.userId = userId;
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
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

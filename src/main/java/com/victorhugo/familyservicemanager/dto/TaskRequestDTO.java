package com.victorhugo.familyservicemanager.dto;

import com.victorhugo.familyservicemanager.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequestDTO {

    //fields

    @NotBlank(message = "no puede ir vacio")
    @Size(max = 100,message = "no puedes exeder mas de 100 caracteres")
    private String description;
    private Long userId;

    //@NotNull(message = "it cant be null victor")
    private TaskStatus status;

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

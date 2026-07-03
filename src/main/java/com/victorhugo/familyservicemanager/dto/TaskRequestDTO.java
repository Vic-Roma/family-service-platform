package com.victorhugo.familyservicemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequestDTO {

    //fields

    @NotBlank(message = "no puede ir vacio")
    @Size(max = 100,message = "no puedes exeder mas de 100 caracteres")
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

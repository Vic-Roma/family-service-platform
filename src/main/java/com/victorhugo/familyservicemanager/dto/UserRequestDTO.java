package com.victorhugo.familyservicemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    //fields

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    //Constructors
    public UserRequestDTO(){}
    public UserRequestDTO(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}

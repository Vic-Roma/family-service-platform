package com.victorhugo.familyservicemanager.dto;

public class UserResponseDTO {

    //field
    private Long user_id;
    private String name;

    //Empity Constructor
    public UserResponseDTO(){}

    //Constructor
    public UserResponseDTO(Long user_id, String name){
        this.user_id = user_id;
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

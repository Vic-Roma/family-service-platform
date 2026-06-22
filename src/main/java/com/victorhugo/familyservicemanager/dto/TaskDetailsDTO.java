package com.victorhugo.familyservicemanager.dto;

public class TaskDetailsDTO {

    //fields
    private Long id;
    private String description;
    private UserSummaryDTO userSummaryDTO;

    //Constructors
    public TaskDetailsDTO(){}
    public TaskDetailsDTO(Long id,String description,UserSummaryDTO userSummaryDTO){
        this.id = id;
        this.description = description;
        this.userSummaryDTO = userSummaryDTO;
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
}

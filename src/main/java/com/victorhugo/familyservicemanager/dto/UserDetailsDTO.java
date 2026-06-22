package com.victorhugo.familyservicemanager.dto;

import java.util.List;

public class UserDetailsDTO {

    //fields
    private Long id;
    private String name;
    private List<TaskSummaryDTO> tasks;

    //empity constructor
    public UserDetailsDTO(){}

    public UserDetailsDTO(Long id,String name, List<TaskSummaryDTO> tasks){
        this.id = id;
        this.name =name;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskSummaryDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskSummaryDTO> tasks) {
        this.tasks = tasks;
    }
}

package com.victorhugo.familyservicemanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")

public class User {

    //Empity Constructor
    public User(){};

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //Getters and SEtter

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
}

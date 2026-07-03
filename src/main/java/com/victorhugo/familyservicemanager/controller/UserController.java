package com.victorhugo.familyservicemanager.controller;

import com.victorhugo.familyservicemanager.dto.UserDetailsDTO;
import com.victorhugo.familyservicemanager.dto.UserRequestDTO;
import com.victorhugo.familyservicemanager.dto.UserResponseDTO;
import com.victorhugo.familyservicemanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //DY
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Endpoints

    //Create a user
    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO user){
        return userService.createUser(user);
    }

    //Create a bunch of users
    @PostMapping("/batch")
    public List<UserResponseDTO> createUsers(@RequestBody @Valid List<UserRequestDTO> requests){
        return userService.createUsers(requests);
    }

    //Get all users
    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    //Get user Details
    @GetMapping("/{id}")
    public UserDetailsDTO getUserDetails(@PathVariable Long id){
        return userService.getUserDetails(id);
    }


    //Modify all from user
    @PutMapping("/{id}")
    public UserDetailsDTO putUser(@Valid @RequestBody UserRequestDTO dto, @PathVariable Long id){
        return userService.putUser(dto,id);
    }

    //Delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}

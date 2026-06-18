package com.victorhugo.familyservicemanager.controller;

import com.victorhugo.familyservicemanager.model.User;
import com.victorhugo.familyservicemanager.service.UserService;
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
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    //Create a bunch of users
    @PostMapping("/batch")
    public List<User> createUsers(@RequestBody List<User> users){
        return userService.createUsers(users);
    }

    //Get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    //Modify all from user
    @PutMapping("/{id}")
    public User putUser(@RequestBody User user, @PathVariable Long id){
        return userService.putUser(user,id);
    }

    //Delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}

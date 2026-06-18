package com.victorhugo.familyservicemanager.service;

import com.victorhugo.familyservicemanager.model.User;
import com.victorhugo.familyservicemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //DY
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //methods

    //create a new user
    public User createUser(User user){
        return userRepository.save(user);
    }

    //Create a bunch of users
    public List<User> createUsers(List<User> users){
        return userRepository.saveAll(users);
    }

    //Get all user
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //modify all from a user
    public User putUser(User user, Long id){

        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setName(user.getName());

        return userRepository.save(existingUser);
    }

    //Delete user
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}

package com.victorhugo.familyservicemanager.service;

import com.victorhugo.familyservicemanager.dto.TaskSummaryDTO;
import com.victorhugo.familyservicemanager.dto.UserDTO;
import com.victorhugo.familyservicemanager.dto.UserDetailsDTO;
import com.victorhugo.familyservicemanager.model.Task;
import com.victorhugo.familyservicemanager.model.User;
import com.victorhugo.familyservicemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User i: users){
            usersDTO.add(toUserDTO(i));
        }
        return usersDTO;
    }

    //Get user Details
    public UserDetailsDTO getUserDetails(Long id){
        User existingUser = userRepository.findById(id).orElseThrow();
        return toUserDetailsDTO(existingUser);

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

    //turn User into UserDTO
    private UserDTO toUserDTO(User user){
        UserDTO u = new UserDTO();
        u.setUser_id(user.getId());
        u.setName(user.getName());
        return u;
    }

    private UserDetailsDTO toUserDetailsDTO(User user){
        //User trae consigo List<User> que hay que convertir a List<TaskSummaryDTO>
        List<TaskSummaryDTO> taskSummaryDTOS = new ArrayList<>();

        for (Task i:user.getTasks()){
            taskSummaryDTOS.add(toTaskSummaryDTO(i));
        }

        return new UserDetailsDTO(
                user.getId(),
                user.getName(),
                taskSummaryDTOS
        );

    }

    private TaskSummaryDTO toTaskSummaryDTO(Task task){
        return new TaskSummaryDTO(
                task.getId(),
                task.getDescription()
        );
    }
}

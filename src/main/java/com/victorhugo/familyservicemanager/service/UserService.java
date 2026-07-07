package com.victorhugo.familyservicemanager.service;

import com.victorhugo.familyservicemanager.dto.TaskSummaryDTO;
import com.victorhugo.familyservicemanager.dto.UserRequestDTO;
import com.victorhugo.familyservicemanager.dto.UserResponseDTO;
import com.victorhugo.familyservicemanager.dto.UserDetailsDTO;
import com.victorhugo.familyservicemanager.exception.UserNotFoundException;
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
    public UserResponseDTO createUser(UserRequestDTO user){

        //from UserRquestDTO to User
        User newUser = new User();
        newUser.setName(user.getName());
        User userSaved = userRepository.save(newUser);

        //User to UserResponseDTO
        return  toUserDTO(userSaved);
    }

    //Create a bunch of users
    public List<UserResponseDTO> createUsers(List<UserRequestDTO> requests){

        //List<User> newUsers = new ArrayList<>();
        List<UserResponseDTO> usersResponse = new ArrayList<>();
        for(UserRequestDTO i:requests){
            User user = new User();
            user.setName(i.getName());
            userRepository.save(user);

            usersResponse.add(toUserDTO(user));
        }

        return usersResponse;
    }

    //Get all user
    public List<UserResponseDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> usersDTO = new ArrayList<>();
        for(User i: users){
            usersDTO.add(toUserDTO(i));
        }
        return usersDTO;
    }

    //Get user Details
    public UserDetailsDTO getUserDetails(Long id){
        User existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return toUserDetailsDTO(existingUser);

    }

    //modify all from a user
    public UserDetailsDTO putUser(UserRequestDTO dto, Long id){

        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setName(dto.getName());

        User userSaved = userRepository.save(existingUser);

        return toUserDetailsDTO(userSaved);
    }

    //Delete user
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //turn User into UserDTO
    private UserResponseDTO toUserDTO(User user){
        UserResponseDTO u = new UserResponseDTO();
        u.setUser_id(user.getId());
        u.setName(user.getName());
        return u;
    }

    private UserDetailsDTO toUserDetailsDTO(User user){
        //User trae consigo List<Task> que hay que convertir a List<TaskSummaryDTO>
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

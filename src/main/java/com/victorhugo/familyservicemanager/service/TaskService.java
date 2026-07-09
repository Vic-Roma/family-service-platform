package com.victorhugo.familyservicemanager.service;


import com.victorhugo.familyservicemanager.dto.*;
import com.victorhugo.familyservicemanager.exception.TaskNotFoundException;
import com.victorhugo.familyservicemanager.exception.UserNotFoundException;
import com.victorhugo.familyservicemanager.model.Task;
import com.victorhugo.familyservicemanager.model.User;
import com.victorhugo.familyservicemanager.repository.TaskRepository;
import com.victorhugo.familyservicemanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    //DY
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    //methods

    //get all tasks
    public List<TaskResponseDTO> getAllTasks(){
        List<TaskResponseDTO> taskResponseDTOS = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();
        for(Task t : tasks){
            taskResponseDTOS.add(new TaskResponseDTO(
                    t.getId(),
                    t.getDescription(),
                    t.getUser() != null ? t.getUser().getId() : null
            ));
        }

        return taskResponseDTOS;
    }

    //Get a task with details
    public TaskDetailsDTO getTaskDetails(Long id){
        Task existingTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        TaskDetailsDTO taskDetailsDTO = new TaskDetailsDTO();

        taskDetailsDTO.setId(existingTask.getId());
        taskDetailsDTO.setDescription(existingTask.getDescription());

        if(existingTask.getUser() != null){
            //Convert user to UserSummaryDTO
            taskDetailsDTO.setUserSummaryDTO(toUserSummaryDTO(existingTask.getUser())
            );
        }
        return taskDetailsDTO;
    }

    //Create a new task
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO){

        Task newTask = new Task();
        //if it has userId first checks if it exists
        if(taskRequestDTO.getUserId() != null) {
            User existingUser = userRepository
                    .findById(taskRequestDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(taskRequestDTO.getUserId()));
            newTask.setUser(existingUser);
        }

        newTask.setDescription(taskRequestDTO.getDescription());
        taskRepository.save(newTask);

        return toTaskResponseDTO(newTask);
    }

    //Create a bunch of tasks
    public List<TaskResponseDTO> createTasks(List<TaskRequestDTO> dtos){

        List<Task> newTasks = new ArrayList<>();
        List<TaskResponseDTO> taskResponseDTOS = new ArrayList<>();
        for(TaskRequestDTO requestDTO:dtos){

            Task task = new Task();

            if (requestDTO.getUserId() != null){
                User existingUser = userRepository
                        .findById(requestDTO.getUserId())
                        .orElseThrow(() -> new UserNotFoundException(requestDTO.getUserId()));

                task.setUser(existingUser);
            }

            task.setDescription(requestDTO.getDescription());

            newTasks.add(task);
          //  taskRepository.save(task);

        }

        taskRepository.saveAll(newTasks);
        for(Task task: newTasks){
            taskResponseDTOS.add(toTaskResponseDTO(task));
        }

        return taskResponseDTOS;
    }

    //moodify all from a single record
    public TaskResponseDTO putTask(Long id, TaskRequestDTO dto){

        Task existingTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        User exintingUser = userRepository
                .findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));

        existingTask.setDescription(dto.getDescription());
        existingTask.setUser(exintingUser);

        Task savedTask = taskRepository.save(existingTask);

        return toTaskResponseDTO(savedTask);
    }

    //delete a single task
    public void deleteTaks(Long id){
        taskRepository.deleteById(id);
    }

    //patch task to user con dTO
    public TaskResponseDTO patchTask(Long id, PatchTaskDTO dto){
        Task existingTask = taskRepository.findById(id).orElseThrow();

        if (dto.getDescription() != null) {
            existingTask.setDescription(dto.getDescription());
        }

        if(dto.getUserId() != null){
            User user = userRepository.findById(dto.getUserId()).orElseThrow();
            existingTask.setUser(user);
        }
        taskRepository.save(existingTask);

        //Conversion a DTOTask to Task
        return toTaskResponseDTO(existingTask);
    }

    private UserSummaryDTO toUserSummaryDTO(User user){
        return new UserSummaryDTO(
                user.getId(),
                user.getName()
        );

    }

    private TaskResponseDTO toTaskResponseDTO(Task existingTask){
        return new TaskResponseDTO(
                existingTask.getId(),
                existingTask.getDescription(),
                existingTask.getUser() != null ? existingTask.getUser().getId() : null
        );

    }
}

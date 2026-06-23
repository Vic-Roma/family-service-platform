package com.victorhugo.familyservicemanager.service;


import com.victorhugo.familyservicemanager.dto.*;
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
        Task existingTask = taskRepository.findById(id).orElseThrow();

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
                    .orElseThrow();
            newTask.setUser(existingUser);
        }

        newTask.setDescription(taskRequestDTO.getDescription());
        taskRepository.save(newTask);

        return toTaskResponseDTO(newTask);
    }

    //Create a bunch of tasks
    public List<Task> createTasks(List<Task> tasks){
        return taskRepository.saveAll(tasks);
    }

    //moodify all from a single record
    public Task putTask(Long id, Task task){
        Task existingTask = taskRepository.findById(id).orElseThrow();
        existingTask.setDescription(task.getDescription());

        return taskRepository.save(existingTask);
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

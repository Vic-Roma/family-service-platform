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
    public List<TaskDTO> getAllTasks(){
        List<TaskDTO> taskDTOS = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();
        for(Task t : tasks){
            taskDTOS.add(new TaskDTO(
                    t.getId(),
                    t.getDescription(),
                    t.getUser() != null ? t.getUser().getId() : null
            ));
        }

        return taskDTOS;
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
    public Task createTask(Task task){
        return taskRepository.save(task);
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
    public TaskDTO patchTask(Long id, PatchTaskDTO dto){
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
        TaskDTO taskDTO = new TaskDTO(
                existingTask.getId(),
                existingTask.getDescription(),
                existingTask.getUser() != null ? existingTask.getUser().getId() : null
        );


        return taskDTO;
    }

    private UserSummaryDTO toUserSummaryDTO(User user){
        return new UserSummaryDTO(
                user.getId(),
                user.getName()
        );

    }
}

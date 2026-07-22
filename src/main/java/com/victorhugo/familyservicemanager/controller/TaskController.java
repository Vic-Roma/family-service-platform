package com.victorhugo.familyservicemanager.controller;

import com.victorhugo.familyservicemanager.dto.PatchTaskDTO;
import com.victorhugo.familyservicemanager.dto.TaskDetailsDTO;
import com.victorhugo.familyservicemanager.dto.TaskRequestDTO;
import com.victorhugo.familyservicemanager.dto.TaskResponseDTO;
import com.victorhugo.familyservicemanager.enums.TaskStatus;
import com.victorhugo.familyservicemanager.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/task")
public class TaskController {

    //DY
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    //endpoints

    //Get all tasks
    @GetMapping
    public List<TaskResponseDTO> getAllTasks(){
        return taskService.getAllTasks();
    }

    //Get a task with details
    @GetMapping("/{id}")
    public TaskDetailsDTO getTaskDetails(
            @Positive(message = "Id must be positive")
            @PathVariable Long id){
        return taskService.getTaskDetails(id);
    }


    //Get Tasks by status
    @GetMapping("/status/{status}")
    public List<TaskResponseDTO> tasksByStatus(
            @PathVariable TaskStatus status){
        return taskService.tasksByStatus(status);
    }

    //Create a single task
    @PostMapping
    public TaskResponseDTO createTask(
            @Valid
            @RequestBody TaskRequestDTO taskRequestDTO){
        return taskService.createTask(taskRequestDTO);
    }

    //Create a bunch of tasks
    @PostMapping("/batch")
    public List<TaskResponseDTO> createTasks(
            @Valid
            @RequestBody List<TaskRequestDTO> dto){
        return taskService.createTasks(dto);
    }

    //modify data from a single task
    @PutMapping("/{id}")
    public TaskResponseDTO putTask(@PathVariable Long id, @RequestBody TaskRequestDTO dto){
        return taskService.putTask(id,dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTaks(id);
    }

    //patch task
    @PatchMapping("/{id}")
    public TaskResponseDTO patchTask(@PathVariable Long id, @RequestBody PatchTaskDTO dto) {
        return taskService.patchTask(id, dto);
    }

}

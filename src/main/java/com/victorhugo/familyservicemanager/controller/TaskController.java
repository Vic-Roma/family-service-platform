package com.victorhugo.familyservicemanager.controller;

import com.victorhugo.familyservicemanager.dto.*;
import com.victorhugo.familyservicemanager.model.Task;
import com.victorhugo.familyservicemanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TaskDetailsDTO getTaskDetails(@PathVariable Long id){
        return taskService.getTaskDetails(id);
    }

    //Create a single task
    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO taskRequestDTO){
        return taskService.createTask(taskRequestDTO);
    }

    //Create a bunch of tasks
    @PostMapping("/batch")
    public List<Task> createTasks(@RequestBody List<Task> tasks){
        return taskService.createTasks(tasks);
    }

    //modify data from a single task
    @PutMapping("/{id}")
    public Task putTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.putTask(id,task);
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

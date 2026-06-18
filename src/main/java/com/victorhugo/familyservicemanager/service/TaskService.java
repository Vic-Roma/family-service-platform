package com.victorhugo.familyservicemanager.service;


import com.victorhugo.familyservicemanager.model.Task;
import com.victorhugo.familyservicemanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    //DY
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    //methods

    //get all tasks
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
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
}

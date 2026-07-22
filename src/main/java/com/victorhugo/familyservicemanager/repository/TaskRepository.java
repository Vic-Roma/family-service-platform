package com.victorhugo.familyservicemanager.repository;

import com.victorhugo.familyservicemanager.enums.TaskStatus;
import com.victorhugo.familyservicemanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByStatus(TaskStatus status);

}

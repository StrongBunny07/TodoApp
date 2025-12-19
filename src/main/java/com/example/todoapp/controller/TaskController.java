package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/users/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{userId}")
    public Task createTask(
            @PathVariable Long userId,
            @RequestBody Task task
    ) {
        return taskService.addTask(userId, task);
    }

    @GetMapping("/{userId}")
    public List<Task> getTask(@PathVariable Long userId) {
        return taskService.getTaskByUser(userId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping
    public List<Task> getTask() {
        return null;
    }
}

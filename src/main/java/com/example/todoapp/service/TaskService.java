package com.example.todoapp.service;

import com.example.todoapp.model.User;
import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task addTask(Long userId, Task task) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getTaskByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + taskId));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setStatus(updatedTask.getStatus());
        return taskRepository.save(existingTask);
    }
}

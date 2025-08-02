package com.railse.hiring.workforcemgmt.controller;

import com.railse.hiring.workforcemgmt.dto.CreateTaskRequest;
import com.railse.hiring.workforcemgmt.dto.TaskDto;
import com.railse.hiring.workforcemgmt.service.TaskService;
import com.railse.hiring.workforcemgmt.util.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto createTask(@RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable String id, @RequestBody CreateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }

    @PostMapping("/{id}/assign/{staffId}")
    public TaskDto assignTaskToStaff(@PathVariable String id, @PathVariable String staffId) {
        return taskService.assignTaskToStaff(id, staffId);
    }

    @PostMapping("/{id}/status")
    public TaskDto changeStatus(@PathVariable String id, @RequestParam String value) {
        return taskService.updateTaskStatus(id, value);
}


    @PostMapping("/{id}/complete")
    public TaskDto completeTask(@PathVariable String id) {
        return taskService.completeTask(id);
    }

    @PostMapping("/{id}/comments")
    public String addComment(@PathVariable String id, @RequestParam String text) {
        taskService.addCommentToTask(id, text);
        return "Comment added successfully";
    }

    @GetMapping("/priority")
    public List<TaskDto> getTasksByPriority(@RequestParam String value) {
        return taskService.getTasksByPriority(value);
    }

    @GetMapping("/range")
    public List<TaskDto> getTasksByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return taskService.getTasksByDateRange(from, to);
    }
}

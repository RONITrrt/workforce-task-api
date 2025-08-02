package com.railse.hiring.workforcemgmt.service;

import com.railse.hiring.workforcemgmt.data.DataStore;
import com.railse.hiring.workforcemgmt.dto.CreateTaskRequest;
import com.railse.hiring.workforcemgmt.dto.TaskDto;
import com.railse.hiring.workforcemgmt.mapper.TaskMapper;
import com.railse.hiring.workforcemgmt.model.Staff;
import com.railse.hiring.workforcemgmt.model.Task;
import com.railse.hiring.workforcemgmt.util.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskMapper taskMapper = TaskMapper.INSTANCE;

    public TaskDto createTask(CreateTaskRequest request) {
        Staff staff = DataStore.staffMembers.get(request.getStaffId());

        if (staff == null) {
            staff = new Staff(request.getStaffId(), "Staff " + request.getStaffId());
            DataStore.staffMembers.put(request.getStaffId(), staff);
        }

        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTitle(request.getTitle());
        task.setStartDate(request.getStartDate());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());
        task.setStatus(TaskStatus.ACTIVE);
        task.setAssignedTo(staff);
        task.setComments(new ArrayList<>());

        DataStore.tasks.put(task.getId(), task);
        return taskMapper.toDto(task);
    }

    public List<TaskDto> getAllTasks() {
        return DataStore.tasks.values().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(String id) {
        Task task = DataStore.tasks.get(id);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        return taskMapper.toDto(task);
    }

    public TaskDto updateTask(String id, CreateTaskRequest request) {
        Task task = DataStore.tasks.get(id);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }

        task.setTitle(request.getTitle());
        task.setStartDate(request.getStartDate());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());

        return taskMapper.toDto(task);
    }

    public void deleteTask(String id) {
        if (DataStore.tasks.remove(id) == null) {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    public TaskDto assignTaskToStaff(String taskId, String staffId) {
        Task task = DataStore.tasks.get(taskId);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        Staff staff = DataStore.staffMembers.get(staffId);
        if (staff == null) {
            staff = new Staff(staffId, "Staff " + staffId);
            DataStore.staffMembers.put(staffId, staff);
        }

        task.setAssignedTo(staff);
        return taskMapper.toDto(task);
    }

    public TaskDto updateTaskStatus(String taskId, String statusValue) {
        Task task = DataStore.tasks.get(taskId);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }

        TaskStatus status;
        try {
            status = TaskStatus.valueOf(statusValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + statusValue);
        }

        task.setStatus(status);
        return taskMapper.toDto(task);
    }

    public TaskDto completeTask(String taskId) {
        return updateTaskStatus(taskId, "COMPLETED");
    }

    public void addCommentToTask(String taskId, String text) {
        Task task = DataStore.tasks.get(taskId);
        if (task == null) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        task.getComments().add(text);
    }

    public List<TaskDto> getTasksByPriority(String priority) {
        return DataStore.tasks.values().stream()
                .filter(task -> task.getPriority().name().equalsIgnoreCase(priority))
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksByDateRange(LocalDate from, LocalDate to) {
        return DataStore.tasks.values().stream()
                .filter(task -> !task.getStartDate().isBefore(from) && !task.getDueDate().isAfter(to))
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }
}

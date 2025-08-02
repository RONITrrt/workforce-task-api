package com.railse.hiring.workforcemgmt.dto;

import com.railse.hiring.workforcemgmt.util.TaskPriority;
import com.railse.hiring.workforcemgmt.util.TaskStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TaskDto {
    private String id;
    private String title;
    private TaskStatus status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private TaskPriority priority;
    private String assignedTo;
    private List<String> comments;
    private List<String> activityHistory;
}

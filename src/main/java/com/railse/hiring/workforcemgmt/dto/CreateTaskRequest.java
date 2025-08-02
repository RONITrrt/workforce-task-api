package com.railse.hiring.workforcemgmt.dto;

import com.railse.hiring.workforcemgmt.util.TaskPriority;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTaskRequest {
    private String title;
    private String staffId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private TaskPriority priority;
}

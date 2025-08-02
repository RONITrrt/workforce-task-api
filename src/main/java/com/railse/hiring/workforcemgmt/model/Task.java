package com.railse.hiring.workforcemgmt.model;

import com.railse.hiring.workforcemgmt.util.TaskPriority;
import com.railse.hiring.workforcemgmt.util.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private String title;
    private TaskStatus status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private TaskPriority priority;
    private Staff assignedTo;

    private List<String> comments = new ArrayList<>();
    private List<String> activityHistory = new ArrayList<>();
}

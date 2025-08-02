package com.railse.hiring.workforcemgmt.data;

import com.railse.hiring.workforcemgmt.model.Staff;
import com.railse.hiring.workforcemgmt.model.Task;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
    public static final Map<String, Task> tasks = new HashMap<>();
    public static final Map<String, Staff> staffMembers = new HashMap<>();
}

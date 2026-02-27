package com.gcmgroup.taskManagerScaler.entity;

import java.util.Date;

import lombok.Data;

@Data
public class TaskEntity {
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private Date taskDeadLine;
    private Boolean taskStatus = false;
}

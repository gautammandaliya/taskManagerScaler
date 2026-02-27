package com.gcmgroup.taskManagerScaler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDTO {

    String taskTitle;
    String taskDescription;
    String taskDeadline;

}
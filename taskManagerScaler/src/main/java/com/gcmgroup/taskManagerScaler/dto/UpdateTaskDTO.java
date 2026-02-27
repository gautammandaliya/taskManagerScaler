package com.gcmgroup.taskManagerScaler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {

    private String taskDescription;
    private String taskDeadline;
    private Boolean taskStatus;
}

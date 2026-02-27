package com.gcmgroup.taskManagerScaler.dto;

import java.util.ArrayList;
import java.util.Date;

import com.gcmgroup.taskManagerScaler.entity.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {

    // Option 1
    /* private TaskEntity taskEntity;
    private ArrayList<NoteEntity> noteEntities; */

    // Option 2
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private Date taskDeadLine;
    private Boolean taskStatus;
    private ArrayList<NoteEntity> noteEntities;
}
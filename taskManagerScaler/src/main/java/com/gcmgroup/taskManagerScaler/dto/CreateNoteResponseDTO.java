package com.gcmgroup.taskManagerScaler.dto;

import com.gcmgroup.taskManagerScaler.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNoteResponseDTO {

    private Long taskId;
    private NoteEntity noteEntity;
}

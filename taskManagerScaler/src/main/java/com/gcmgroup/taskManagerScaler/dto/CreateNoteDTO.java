package com.gcmgroup.taskManagerScaler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateNoteDTO {

    private String noteTitle;
    private String noteContent;
}

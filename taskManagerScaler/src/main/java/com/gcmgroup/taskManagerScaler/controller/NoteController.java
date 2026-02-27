package com.gcmgroup.taskManagerScaler.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcmgroup.taskManagerScaler.dto.CreateNoteDTO;
import com.gcmgroup.taskManagerScaler.dto.CreateNoteResponseDTO;
import com.gcmgroup.taskManagerScaler.entity.NoteEntity;
import com.gcmgroup.taskManagerScaler.service.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<NoteEntity>> getNotesForTaskId(@PathVariable("taskId") Long taskId) {
        var notes = noteService.getNotesForTaskId(taskId);
        return ResponseEntity.ok(notes);
        // return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateNoteResponseDTO> createNoteForTaskId(@PathVariable("taskId") Long taskId, @RequestBody CreateNoteDTO createNote) {
        var note = noteService.createNoteForTaskId(taskId, createNote.getNoteTitle(), createNote.getNoteContent());

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }
}

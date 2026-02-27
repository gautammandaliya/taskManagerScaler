package com.gcmgroup.taskManagerScaler.controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcmgroup.taskManagerScaler.dto.CreateTaskDTO;
import com.gcmgroup.taskManagerScaler.dto.ErrorResponseDTO;
import com.gcmgroup.taskManagerScaler.dto.TaskResponseDTO;
import com.gcmgroup.taskManagerScaler.dto.UpdateTaskDTO;
import com.gcmgroup.taskManagerScaler.entity.TaskEntity;
import com.gcmgroup.taskManagerScaler.service.NoteService;
import com.gcmgroup.taskManagerScaler.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final NoteService noteService;

    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskEntity> createTask(@RequestBody CreateTaskDTO createTask) throws ParseException {
        var createdTask = taskService.addTask(createTask.getTaskTitle(), createTask.getTaskDescription(), createTask.getTaskDeadline());
        return ResponseEntity.ok(createdTask);
        // return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
        // return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Long id) {
        var task = taskService.getTaskById(id);
        var notes = noteService.getNotesForTaskId(id);

        if(task == null) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        // Option 1:
        // return ResponseEntity.ok(new TaskResponseDTO(task, notes));
        // return new ResponseEntity(task, HttpStatus.OK);

        //Option 2: Using ModelMapper
        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNoteEntities(notes);
        return ResponseEntity.ok(taskResponse);
        // return new ResponseEntity(task, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Long id, @RequestBody UpdateTaskDTO updateTask) throws ParseException {
        var updatedTask = taskService.updateTask(id, updateTask.getTaskDescription(), updateTask.getTaskDeadline(), updateTask.getTaskStatus());

        if(updatedTask == null) {
            return ResponseEntity.notFound().build();
            // return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(updatedTask);
        // return ResponseEntity.status(HttpStatus.CREATED).body(updatedTask);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
        if(e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
            // return new ResponseEntity<>(new ErrorResponseDTO("Invalid Date Format"), HttpStatus.BAD_REQUEST);
        }

        e.printStackTrace();

        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("Internal Server Error"));
    }
}

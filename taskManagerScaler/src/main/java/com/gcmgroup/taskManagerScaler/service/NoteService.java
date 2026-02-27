package com.gcmgroup.taskManagerScaler.service;

import com.gcmgroup.taskManagerScaler.entity.NoteEntity;
import com.gcmgroup.taskManagerScaler.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NoteService {

    private TaskService taskService;

    private HashMap<Long, TaskNotesHolder> taskNotesHolderHashMap = new  HashMap<>();

    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder {
        protected long noteId = 1;
        protected ArrayList<NoteEntity> notes = new  ArrayList<>();
    }

    public ArrayList<NoteEntity> getNotesForTaskId(long taskId) {
        TaskEntity task = taskService.getTaskById(taskId);

        if(task == null) {
            return null;
        }

        if (taskNotesHolderHashMap.get(taskId) == null) {
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolderHashMap.get(taskId).notes;
    }

    public NoteEntity createNoteForTaskId(long taskId, String noteTitle, String noteContent) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null) {
            return null;
        }
        if (taskNotesHolderHashMap.get(taskId) == null) {
            taskNotesHolderHashMap.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder = taskNotesHolderHashMap.get(taskId);
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setNoteId(taskNotesHolder.noteId);
        noteEntity.setNoteTitle(noteTitle);
        noteEntity.setNoteContent(noteContent);

        taskNotesHolder.notes.add(noteEntity);
        taskNotesHolder.noteId++;

        return noteEntity;
    }
}

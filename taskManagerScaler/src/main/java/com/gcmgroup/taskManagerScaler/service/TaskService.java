package com.gcmgroup.taskManagerScaler.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.gcmgroup.taskManagerScaler.entity.TaskEntity;

@Service
public class TaskService {

    private ArrayList<TaskEntity> tasks = new  ArrayList<>();
    private long taskId = 1;

    private SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String taskTitle, String taskDescription, String taskDeadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setTaskId(taskId);
        task.setTaskTitle(taskTitle);
        task.setTaskDescription(taskDescription);
        task.setTaskDeadLine(deadlineFormatter.parse(taskDeadline));

        tasks.add(task);
        taskId++;

        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(long id) {
        for (TaskEntity task : tasks) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(Long taskId, String taskDescription, String taskDeadline, Boolean taskStatus) throws ParseException {
        TaskEntity task = getTaskById(taskId);
        if (task == null) {
            return null;
        }

        if (taskDescription != null) {
            task.setTaskDescription(taskDescription);
        }
        if (taskDeadline != null) {
            task.setTaskDeadLine(deadlineFormatter.parse(taskDeadline));
        }
        if (taskStatus != null) {
            task.setTaskStatus(taskStatus);
        }

        return task;
    }
}

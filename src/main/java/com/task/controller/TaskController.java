package com.task.controller;

import com.task.exception.NotFoundException;
import com.task.model.dto.task.TaskByUserAndCreatedAtDto;
import com.task.model.dto.task.TaskDto;
import com.task.model.dto.task.TaskDtoResp;
import com.task.model.entity.Task;
import com.task.service.task.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    private final ITaskService taskService;


    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDtoResp> createdTask(@RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.save(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/task/{taskId}")
    public TaskDtoResp getTaskById(@PathVariable Long taskId) throws NotFoundException {
        TaskDtoResp getTask = taskService.findById(taskId);
        return getTask;
    }

    @PutMapping("/task")
    public ResponseEntity<TaskDtoResp> updateTask(@RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.save(taskDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<String>  deleteTask(@PathVariable Long taskId){
        taskService.delete(taskId);
        return new ResponseEntity<>("Eliminado", HttpStatus.OK);
    }

    @GetMapping("/task/taskByUserAndCreaterAt")
    public List<Task> getTaskByUserIdAndCreatedAt(@RequestBody TaskByUserAndCreatedAtDto taskDto) throws NotFoundException {
        List<Task> taskList = taskService.getTaskByUserAndCreatedAt(taskDto);
        return taskList;
    }
}

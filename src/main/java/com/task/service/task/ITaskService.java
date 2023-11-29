package com.task.service.task;

import com.task.exception.NotFoundException;
import com.task.model.dto.task.TaskByUserAndCreatedAtDto;
import com.task.model.dto.task.TaskDto;
import com.task.model.dto.task.TaskDtoResp;
import com.task.model.entity.Task;

import java.util.List;

public interface ITaskService {
    TaskDtoResp save(TaskDto taskDto);
    TaskDtoResp findById(Long id) throws NotFoundException;
    void delete(Long taskId);
    List<Task> getTaskByUserAndCreatedAt(TaskByUserAndCreatedAtDto taskDto) throws NotFoundException;

}

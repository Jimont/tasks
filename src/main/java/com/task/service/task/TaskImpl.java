package com.task.service.task;

import com.task.commons.MapperUtils;
import com.task.exception.NotFoundException;
import com.task.model.dao.TaskDao;
import com.task.model.dto.task.TaskByUserAndCreatedAtDto;
import com.task.model.dto.task.TaskDto;
import com.task.model.dto.task.TaskDtoResp;
import com.task.model.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskImpl implements ITaskService{
    private final TaskDao taskDao;
    private final MapperUtils mapper;

    public TaskImpl(TaskDao taskDao, MapperUtils mapper) {
        this.taskDao = taskDao;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public TaskDtoResp save(TaskDto taskDto) {
        LocalDateTime datetime = LocalDateTime.now();
        Task task = mapper.map(taskDto, Task.class);
        task.setCreatedAt(datetime);
        Task respCreatedTask = taskDao.save(task);
        TaskDtoResp taskDtoResp = mapper.map(respCreatedTask, TaskDtoResp.class);
        taskDtoResp.setUserId(respCreatedTask.getUser().getUserId());
        return taskDtoResp;
    }
    @Transactional(readOnly = true)
    @Override
    public TaskDtoResp findById(Long id) throws NotFoundException {
        Task getTask =  taskDao.findById(id).orElse(null);
        if(getTask == null){
            throw new NotFoundException("Task is not available");
        }
        TaskDtoResp taskdto = mapper.map(getTask, TaskDtoResp.class);
        taskdto.setUserId(getTask.getUser().getUserId());
        return taskdto;
    }
    @Transactional
    @Override
    public void delete(Long taskId) {
        taskDao.deleteById(taskId);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Task> getTaskByUserAndCreatedAt(TaskByUserAndCreatedAtDto taskDto) throws NotFoundException {
        Long userId = taskDto.getUserId();
        LocalDateTime firsDate = LocalDateTime.parse(taskDto.getFirstDate());
        LocalDateTime lastDate = LocalDateTime.parse(taskDto.getLastDate());
        List<Task> listTask = taskDao.getTaskByUserIdAndCreatedAt(userId, firsDate, lastDate);
        if(listTask.isEmpty()){
            throw new NotFoundException("Tasks is not available");
        }
        return listTask;
    }
}

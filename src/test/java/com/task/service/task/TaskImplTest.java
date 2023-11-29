package com.task.service.task;

import com.task.exception.NotFoundException;
import com.task.model.dto.task.TaskByUserAndCreatedAtDto;
import com.task.model.dto.task.TaskDto;
import com.task.model.dto.task.TaskDtoResp;
import com.task.model.entity.Task;
import com.task.model.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskImplTest {
    @MockBean
    private TaskImpl taskService;
    private TaskDtoResp taskDtoResp;
    private TaskDto taskDto;

    private Task task;

    private User user;

    private TaskByUserAndCreatedAtDto taskByUserAndCreatedAtDto;

    @BeforeEach
    void setUp() throws NotFoundException {
        LocalDateTime datetime = LocalDateTime.now();
        List<Task> taskList = new ArrayList<>();
        task = new Task(1L, "Generar casos de prueba", false, datetime, new User());
        taskList.add(task);

        taskByUserAndCreatedAtDto = new TaskByUserAndCreatedAtDto();
        taskByUserAndCreatedAtDto.setUserId(1L);
        taskByUserAndCreatedAtDto.setFirstDate("2023-11-27T00:00:00");
        taskByUserAndCreatedAtDto.setLastDate("2023-11-30T23:59:50");

        taskDto = new TaskDto();
        taskDto.setTaskId(1L);
        taskDto.setDescription("Participar de la daily");
        taskDto.setIsComplete(true);

        taskDtoResp = new TaskDtoResp();
        taskDtoResp.setTaskId(1L);
        taskDtoResp.setDescription("Participar de la daily");
        taskDtoResp.setIsComplete(true);
        taskDtoResp.setUserId(1L);

        Mockito.when(taskService.save(taskDto)).thenReturn(taskDtoResp);
        Mockito.when(taskService.findById(1L)).thenReturn(taskDtoResp);
        Mockito.when(taskService.getTaskByUserAndCreatedAt(taskByUserAndCreatedAtDto)).thenReturn(taskList);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        TaskDtoResp resp = new TaskDtoResp();
        resp = taskService.save(taskDto);
        assertEquals("Participar de la daily", resp.getDescription());
    }

    @Test
    void findById() throws NotFoundException {
        TaskDtoResp resp = new TaskDtoResp();
        Long id = 1L;
        resp = taskService.findById(id);
        assertEquals(1L, resp.getTaskId());
    }


    @Test
    void getTaskByUserAndCreatedAt() throws NotFoundException {
        List<Task> listTask =taskService.getTaskByUserAndCreatedAt(taskByUserAndCreatedAtDto);
        assertEquals(1, listTask.size());
    }
}
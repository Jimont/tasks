package com.task.service.user;

import com.task.exception.NotFoundException;
import com.task.model.dto.user.UserDto;
import com.task.model.dto.user.UserDtoResp;
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
class UserImplTest {
    @MockBean
    private UserImpl userService;
    UserDto userDto;
    UserDtoResp userDtoResp;
    private Task task;

    @BeforeEach
    void setUp() throws NotFoundException {
        LocalDateTime datetime = LocalDateTime.now();
        List<Task> taskList = new ArrayList<>();
        task = new Task(1L, "Generar casos de prueba", false, datetime, new User());
        taskList.add(task);

        userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setUserName("Carlos Perez");
        userDto.setUserEmail("carlos.perez@correo.com");

        userDtoResp = new UserDtoResp();
        userDtoResp.setUserId(1L);
        userDtoResp.setUserName("Carlos Perez");
        userDtoResp.setUserEmail("carlos.perez@correo.com");

        Mockito.when(userService.save(userDto)).thenReturn(userDtoResp);
        Mockito.when(userService.findById(1L)).thenReturn(userDtoResp);
        Mockito.when(userService.taskByUser(1L)).thenReturn(taskList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        UserDtoResp userDtoR = new UserDtoResp();
        userDtoR = userService.save(userDto);
        assertEquals("carlos.perez@correo.com", userDtoR.getUserEmail());
    }

    @Test
    void findById() throws NotFoundException {
        UserDtoResp userDtoR = new UserDtoResp();
        userDtoR = userService.findById(1L);
        assertEquals("Carlos Perez", userDtoR.getUserName());
    }

    @Test
    void delete() {
    }

    @Test
    void taskByUser() throws NotFoundException {
        List<Task> tasksList = new ArrayList<>();
        tasksList = userService.taskByUser(1L);
        assertEquals(1, tasksList.size());

    }
}
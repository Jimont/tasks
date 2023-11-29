package com.task.controller;

import com.task.exception.NotFoundException;
import com.task.model.dto.user.UserDto;
import com.task.model.dto.user.UserDtoResp;
import com.task.model.entity.Task;
import com.task.service.user.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDtoResp> createdTask(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDtoResp> getTaskById(@PathVariable Long userId) throws NotFoundException {
        UserDtoResp getUser = userService.findById(userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDtoResp> updateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String>  deleteUser(@PathVariable Long userId){
        userService.delete(userId);
        return new ResponseEntity<>("Eliminado", HttpStatus.OK);
    }

    @GetMapping("/user/taskByUser/{userId}")
    public List<Task> getTaskByUserId(@PathVariable Long userId) throws NotFoundException {
        List<Task> taskList = userService.taskByUser(userId);
        return taskList;
    }


}

package com.task.service.user;

import com.task.exception.NotFoundException;
import com.task.model.dto.user.UserDto;
import com.task.model.dto.user.UserDtoResp;
import com.task.model.entity.Task;

import java.util.List;

public interface IUserService {
    UserDtoResp save(UserDto userDto);
    UserDtoResp findById(Long id) throws NotFoundException;
    void delete(Long userId);

    List<Task> taskByUser(Long userId) throws NotFoundException;
}

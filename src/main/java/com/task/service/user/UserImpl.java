package com.task.service.user;

import com.task.commons.MapperUtils;
import com.task.exception.NotFoundException;
import com.task.model.dao.UserDao;
import com.task.model.dto.user.UserDto;
import com.task.model.dto.user.UserDtoResp;
import com.task.model.entity.Task;
import com.task.model.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserImpl implements IUserService{
    private final UserDao userDao;
    private final MapperUtils mapper;



    public UserImpl(UserDao userDao, MapperUtils mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }
    @Transactional
    @Override
    public UserDtoResp save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        User userCreated = userDao.save(user);
        return mapper.map(userCreated, UserDtoResp.class);
    }
    @Transactional(readOnly = true)
    @Override
    public UserDtoResp findById(Long id) throws NotFoundException {
        User userGet = userDao.findById(id).orElse(null);
        if(userGet == null){
            throw new NotFoundException("User is not available");
        }
        return mapper.map(userGet, UserDtoResp.class);
    }
    @Transactional
    @Override
    public void delete(Long userId) {
        userDao.deleteById(userId);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Task> taskByUser(Long userId) throws NotFoundException {
        User userGet = userDao.findById(userId).orElse(null);
        if(userGet == null){
            throw new NotFoundException("User is not available");
        }
        return userGet.getTasks();
    }
}

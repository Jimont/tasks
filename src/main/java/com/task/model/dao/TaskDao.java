package com.task.model.dao;

import com.task.model.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskDao extends CrudRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.user.userId = ?1 AND (t.createdAt > ?2) AND (t.createdAt < ?3)")
    List<Task> getTaskByUserIdAndCreatedAt(Long userId, LocalDateTime firsdate, LocalDateTime lastDate);
}

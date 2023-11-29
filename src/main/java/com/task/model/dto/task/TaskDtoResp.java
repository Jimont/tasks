package com.task.model.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskDtoResp{
    @JsonProperty("task_id")
    private Long taskId;
    private String description;
    @JsonProperty("is_complete")
    private Boolean isComplete;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("user_id")
    private Long userId;
}

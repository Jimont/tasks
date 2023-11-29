package com.task.model.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.model.entity.User;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskDto {
    @JsonProperty("task_id")
    private long taskId;
    private String description;
    @JsonProperty("is_complete")
    private Boolean isComplete;
    private User user;
    @JsonProperty("user_id")
    private void unpackNested(Long user_id) {
        this.user = new User();
        user.setUserId(user_id);
    }
}

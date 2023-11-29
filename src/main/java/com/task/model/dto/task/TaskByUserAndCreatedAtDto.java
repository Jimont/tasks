package com.task.model.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskByUserAndCreatedAtDto {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("first_date")
    private String firstDate;
    @JsonProperty("last_date")
    private String lastDate;
}

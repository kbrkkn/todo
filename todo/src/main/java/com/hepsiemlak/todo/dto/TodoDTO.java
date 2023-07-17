package com.hepsiemlak.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hepsiemlak.todo.model.TodoStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoDTO {

    private String id;

    @NotNull
    @NotEmpty
    private String title;

    private TodoStatus status;

    private String creationTime;

    private String updatedTime;

    @NotNull
    @NotEmpty
    private String category;

    @Positive(message = "priority must be greater than 0")
    private Integer priority;
}

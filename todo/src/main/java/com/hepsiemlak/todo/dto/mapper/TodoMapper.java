package com.hepsiemlak.todo.dto.mapper;

import com.hepsiemlak.todo.dto.TodoDTO;
import com.hepsiemlak.todo.model.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class TodoMapper {
    public abstract TodoDTO mapToTodoDTO(Todo todo);

    public abstract List<TodoDTO> mapToTodoDTOs(List<Todo> todos);

    public abstract Todo mapToTodo(TodoDTO todoDTO);

    public abstract List<Todo> mapToTodos(List<TodoDTO> todoDTOs);

}

package com.hepsiemlak.todo.service;

import com.couchbase.client.java.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsiemlak.todo.dto.TodoDTO;
import com.hepsiemlak.todo.dto.mapper.TodoMapper;
import com.hepsiemlak.todo.exception.EntityNotFoundException;
import com.hepsiemlak.todo.model.Todo;
import com.hepsiemlak.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper = Mappers.getMapper(TodoMapper.class);

    public ResponseEntity<String> createTodo(TodoDTO todoDTO) {
        Todo todo = todoMapper.mapToTodo(todoDTO);
        todoRepository.save(todo);
        return new ResponseEntity<>("TODO added.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateTodo(TodoDTO todoDTO) {
        Optional<Todo> existingTodo = todoRepository.findById(todoDTO.getId());
        if (existingTodo.isPresent()) {
            Todo entity = todoMapper.mapToTodo(todoDTO);
            todoRepository.save(entity);
            return new ResponseEntity<>("TODO updated.", HttpStatus.OK);
        }
        throw new EntityNotFoundException("Data not found");
    }

    public ResponseEntity<String> deleteTodo(String id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            todoRepository.deleteById(id);
            String message = "TODO removed.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        throw new EntityNotFoundException("Data not found");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<TodoDTO>> getAllTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        if (todoList.isEmpty()) {
            throw new EntityNotFoundException("Data not found");
        }
        List<TodoDTO> todoDTOList = todoMapper.mapToTodoDTOs(todoList);
        return new ResponseEntity<>(todoDTOList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<TodoDTO>> getFilteredTodoList(TodoDTO todoDTO) throws JsonProcessingException {
        JsonObject jsonObject = JsonObject.fromJson(new ObjectMapper().writeValueAsBytes(todoDTO));
        List<Todo> todoList = todoRepository.getFilteredTodoList(jsonObject);
        if (todoList.isEmpty()) {
            throw new EntityNotFoundException("Data not found");
        }
        List<TodoDTO> todoDTOList = todoMapper.mapToTodoDTOs(todoList);
        return new ResponseEntity<>(todoDTOList, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Todo> getTodoById(String id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            return new ResponseEntity<>(existingTodo.get(), HttpStatus.OK);
        }
        throw new EntityNotFoundException("Data not found");
    }
}

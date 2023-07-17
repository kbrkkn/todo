package com.hepsiemlak.todo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.hepsiemlak.todo.dto.TodoDTO;
import com.hepsiemlak.todo.model.Todo;
import com.hepsiemlak.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
@Valid
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    ResponseEntity<String> createTodo(@RequestBody @Valid TodoDTO todoDTO) {
        return todoService.createTodo(todoDTO);
    }

    @PutMapping
    ResponseEntity<String> updateTodo(@RequestBody @Valid TodoDTO todoDTO) {
        return todoService.updateTodo(todoDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTodo(@PathVariable("id") String id) {
        return todoService.deleteTodo(id);
    }

    @GetMapping
    ResponseEntity<List<TodoDTO>> getAllTodoList() {
        return todoService.getAllTodoList();
    }

    @PostMapping("/filter")
    ResponseEntity<List<TodoDTO>> getFilteredTodoList(@RequestBody @Valid TodoDTO todoDTO) throws JsonProcessingException {
        return todoService.getFilteredTodoList(todoDTO);
    }

    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoDetails(@PathVariable("id") String id) {
        return todoService.getTodoById(id);
    }
}

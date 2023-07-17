package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.dto.TodoDTO;
import com.hepsiemlak.todo.model.Todo;
import com.hepsiemlak.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTodo() {

        TodoDTO todoDTO = new TodoDTO();
        ResponseEntity<String> response = todoService.createTodo(todoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("TODO added.", response.getBody());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void updateTodo() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(UUID.randomUUID().toString());

        Todo todo = new Todo();
        when(todoRepository.findById(eq(todoDTO.getId()))).thenReturn(Optional.of(todo));


        ResponseEntity<String> response = todoService.updateTodo(todoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TODO updated.", response.getBody());
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    void deleteTodo() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(UUID.randomUUID().toString());

        Todo todo = new Todo();
        when(todoRepository.findById(eq(todoDTO.getId()))).thenReturn(Optional.of(todo));


        ResponseEntity<String> response = todoService.deleteTodo(todoDTO.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TODO removed.", response.getBody());
        verify(todoRepository, times(1)).deleteById(todoDTO.getId());
    }

    @Test
    void getTodoById() {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(UUID.randomUUID().toString());

        Todo todo = new Todo();
        when(todoRepository.findById(eq(todoDTO.getId()))).thenReturn(Optional.of(todo));


        ResponseEntity<Todo> response = todoService.getTodoById(todoDTO.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(todoRepository, times(1)).findById(todoDTO.getId());
    }
}
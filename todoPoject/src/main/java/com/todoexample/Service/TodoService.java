package com.todoexample.Service;

import java.util.List;

import com.todoexample.dto.TodoDto;

public interface TodoService {

	public TodoDto createTodo(TodoDto todoDto);

	public TodoDto getByTodoId(Long getId);

	public List<TodoDto> getAllTodoData();

	public TodoDto updatedTodo(Long getId, TodoDto todoDto);

	public void deleteTodo(Long getId);

}

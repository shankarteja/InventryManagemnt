package com.todoexample.Mapping;

import com.todoexample.Entinty.Todo;
import com.todoexample.dto.TodoDto;

public class TodoMapping {

	public static final Todo mapToTodo(TodoDto todoDto) {

		Todo todo = new Todo();
		todo.setId(todoDto.getId());
		todo.setTitle(todoDto.getTitle());
		todo.setCompleted(todoDto.isCompleted());
		todo.setDescription(todoDto.getDescription());
		todo.setStatus(todoDto.getStatus());
		return todo;

	}

	public static final TodoDto mapToTodoDto(Todo todo) {

		TodoDto todoDto = new TodoDto();
		todoDto.setId(todo.getId());
		todoDto.setTitle(todo.getTitle());
		todoDto.setCompleted(todo.isCompleted());
		todoDto.setStatus(todo.getStatus());
		todoDto.setDescription(todo.getDescription());
		return todoDto;

	}

}

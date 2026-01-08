package com.todoexample.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todoexample.Entinty.Todo;
import com.todoexample.Mapping.TodoMapping;
import com.todoexample.Service.TodoService;
import com.todoexample.dto.TodoDto;
import com.todoexample.exception.ResourceNotFoundException;
import com.todoexample.repo.TodoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;

	@Override
	public TodoDto createTodo(TodoDto todoDto) {
		log.info("Creating new todo with title: {}", todoDto.getTitle());
		log.debug("Incoming TodoDto: {}", todoDto);
		/*
		 * Todo todo = new Todo(); todo.setId(todoDto.getId());
		 * todo.setCompleted(todoDto.isCompleted());
		 * todo.setDescription(todoDto.getDescription());
		 * todo.setStatus(todoDto.getStatus()); todo.setTitle(todoDto.getTitle()); Todo
		 * savedtodo = todoRepository.save(todo);
		 * log.info("Todo saved successfully with ID: {}", savedtodo.getId());
		 * 
		 * TodoDto todoDtos = new TodoDto(); todoDtos.setId(savedtodo.getId());
		 * todoDtos.setStatus(savedtodo.getStatus());
		 * todoDtos.setTitle(savedtodo.getTitle());
		 * todoDtos.setDescription(savedtodo.getDescription());
		 * todoDtos.setCompleted(savedtodo.isCompleted());
		 * log.debug("Returning TodoDto: {}", todoDtos); // TodoDto
		 * savedDtos=todoRepository.save(todoDtos); return todoDtos;
		 */

		Todo savedTodos = TodoMapping.mapToTodo(todoDto);
		log.info("Creating new todo with title: {}", todoDto.getTitle());
		log.debug("Incoming TodoDto: {}", todoDto);
		Todo savedTo = todoRepository.save(savedTodos);
		log.debug("Incoming TodoDto: {}", todoDto);
		TodoDto saveTodo = TodoMapping.mapToTodoDto(savedTo);
		return saveTodo;
	}

	@Override
	public TodoDto getByTodoId(Long getId) {
		log.info("🔍 Received request to fetch Todo by ID: {}", getId);
		Todo getById = todoRepository.findById(getId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo Id is not be executed......!"));
		TodoDto saveTodo = TodoMapping.mapToTodoDto(getById);
		log.info("📝 Mapped Todo entity to DTO successfully for ID: {}", getId);
		return saveTodo;
	}

	@Override
	public List<TodoDto> getAllTodoData() {
		log.info("📦 Request received to fetch all Todo records.");
		List<Todo> getData = todoRepository.findAll();
		log.debug("🔍 Retrieved {} Todo entities from the database.", getData.size());
		log.warn("⚠️ No Todo records found in the database.");
		List<TodoDto> getDatsAll = getData.stream().map(TodoMapping::mapToTodoDto)
				.collect(Collectors.toList());
		log.info("✅ Successfully mapped {} Todo entities to DTOs.", getDatsAll.size());
		return getDatsAll;
	}

	@Override
	public TodoDto updatedTodo(Long getId, TodoDto todoDto) {
		log.info("🔍 Received request to fetch Todo by ID: {}", getId);
		Todo updatedTodo = todoRepository.findById(getId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo Id is not be executed......!"));
		// TodoDto saveTodo = TodoMapping.mapToTodoDto(updatedTodo);
		log.info("📝 Mapped Todo entity to DTO successfully for ID: {}", getId);
		updatedTodo.setTitle(todoDto.getTitle());
		updatedTodo.setStatus(todoDto.getStatus());
		updatedTodo.setDescription(todoDto.getDescription());
		updatedTodo.setCompleted(todoDto.isCompleted());
		log.info("🔍 updated request to fetch Todo by ID: {}");
		Todo updatedTodos = todoRepository.save(updatedTodo);
		return TodoMapping.mapToTodoDto(updatedTodos);
	}

	@Override
	public void deleteTodo(Long getId) {
		log.info("Attempting to delete todo with id: {}", getId);
		Todo deleteTodo = todoRepository.findById(getId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo Id is not be executed......!"));
		log.debug("Found todo: {}", deleteTodo);
		todoRepository.delete(deleteTodo);
		log.info("Successfully deleted todo with id: {}", getId);

	}

}

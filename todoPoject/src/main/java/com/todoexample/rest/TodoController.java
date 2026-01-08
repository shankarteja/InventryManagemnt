package com.todoexample.rest;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoexample.Impl.TodoServiceImpl;
import com.todoexample.dto.TodoDto;
import com.todoexample.repo.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private TodoServiceImpl todoServiceImpl;

	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		log.info("POST /todos - Received request to add new todo");
		log.debug("Request body: {}", todoDto);
		TodoDto savedTodo = todoServiceImpl.createTodo(todoDto);
		log.info("Todo created successfully with ID: {}", savedTodo.getId());
		log.debug("Response: {}", savedTodo);
		return new ResponseEntity<TodoDto>(savedTodo, HttpStatus.CREATED);

	}

	@GetMapping("{id}")
	public ResponseEntity<TodoDto> TodoId(@PathVariable("id") Long getId) {
		log.info("📥 Received GET request for Todo with ID: {}", getId);
		TodoDto savedTodos = todoServiceImpl.getByTodoId(getId);
		log.error("❌ Todo not found for ID: {}", getId);
		log.error("⚠️ Unexpected error while fetching Todo with ID: {}");
		return new ResponseEntity<TodoDto>(savedTodos, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<TodoDto>> GetAllData() {
		log.info("📥 Received GET request to fetch all Todos.");
		List<TodoDto> getAllData = todoServiceImpl.getAllTodoData();
		log.info("✅ Successfully fetched {} Todo records.", getAllData.size());
		return new ResponseEntity<List<TodoDto>>(getAllData, HttpStatus.OK);
	}

	@PutMapping("{id}/updated")
	public ResponseEntity<TodoDto> updatedTodo(@PathVariable("id") Long getId, @RequestBody TodoDto todoDto) {
		log.info("Received request to update todo with id: {}", getId);
		log.debug("Update payload: {}", todoDto);
		TodoDto updatedTodo = todoServiceImpl.updatedTodo(getId, todoDto);
		log.info("Successfully updated todo with id: {}", getId);
		return new ResponseEntity<TodoDto>(updatedTodo, HttpStatus.UPGRADE_REQUIRED);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long getId) {
		log.info("Received request to delete todo with id: {}", getId);
		todoServiceImpl.deleteTodo(getId);
		return ResponseEntity.ok("delete Todo Id successfully ..!" + getId);

	}

}
